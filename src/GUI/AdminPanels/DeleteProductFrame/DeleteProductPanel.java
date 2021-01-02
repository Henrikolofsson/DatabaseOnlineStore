package GUI.AdminPanels.DeleteProductFrame;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductPanel extends JPanel{
    private MainController controller;
    private DeleteProductFrame deleteProductFrame;

    private JLabel lblProductToDelete;

    private JComboBox<String> cmbBoxProducts;

    private JButton btnDeleteProduct;

    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;

    public DeleteProductPanel(MainController controller, DeleteProductFrame deleteProductFrame){
        this.controller = controller;
        this.deleteProductFrame = deleteProductFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductToDelete = new JLabel("Select product to delete");
        lblProductToDelete.setMinimumSize(new Dimension(200,20));
        lblProductToDelete.setPreferredSize(new Dimension(200,20));
        lblProductToDelete.setForeground(Color.LIGHT_GRAY);

        cmbBoxProducts = new JComboBox<>();
        for(int i = 0; i < controller.getProducts().size(); i++){
            cmbBoxProducts.addItem(controller.getProducts().get(i));
        }
        cmbBoxProducts.setMinimumSize(new Dimension(140,20));
        cmbBoxProducts.setPreferredSize(new Dimension(140,20));

        btnDeleteProduct = new JButton("Delete product");
        btnDeleteProduct.setSize(new Dimension(150, 25));
        btnDeleteProduct.setPreferredSize(new Dimension(150, 25));
        btnDeleteProduct.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnDeleteProduct.setOpaque(true);
        btnDeleteProduct.setBorderPainted(false);
        btnDeleteProduct.setBackground(Color.decode("#518A3D"));

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(200, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);
        btnExit.setBackground(Color.decode("#518A3D"));

        GRAY_BACKGROUND_COLOR = Color.decode("#2b2b2b");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setBackground(GRAY_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(500, 300));
        setMaximumSize(new Dimension(500,300));
        setMinimumSize(new Dimension(500,300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblProductToDelete, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cmbBoxProducts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnDeleteProduct, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnDeleteProduct.addActionListener(new BtnDeleteProductListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteProductFrame.dispose();
        }
    }

    private class BtnDeleteProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = cmbBoxProducts.getSelectedIndex();
            String productNameToDelete = String.valueOf(cmbBoxProducts.getItemAt(index));
            cmbBoxProducts.removeItemAt(index);

            controller.sendToDelete(productNameToDelete);
            controller.updateProductList();
        }
    }
}
