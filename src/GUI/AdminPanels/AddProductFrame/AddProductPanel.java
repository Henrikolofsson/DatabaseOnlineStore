package GUI.AdminPanels.AddProductFrame;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductPanel extends JPanel{
    private MainController controller;
    private AddProductFrame addProductFrame;

    private JLabel lblProductName;
    private JLabel lblProductQuantity;
    private JLabel lblProductBasePrice;
    private JLabel lblProductSupplier;
    private JLabel lblDiscounts;

    private JTextField txtProductName;
    private JTextField txtProductQuantity;
    private JTextField txtProductBasePrice;
    private JComboBox<String> cmbBoxSupplier;

    private JButton btnAddProduct;
    private JButton btnExit;
    private Color GRAY_BACKGROUND_COLOR;

    public AddProductPanel(MainController controller, AddProductFrame addProductFrame) {
        this.controller = controller;
        this.addProductFrame = addProductFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductName = new JLabel("Product name: ");
        lblProductName.setMinimumSize(new Dimension(120,20));
        lblProductName.setPreferredSize(new Dimension(120,20));
        lblProductName.setForeground(Color.LIGHT_GRAY);

        txtProductName = new JTextField();
        txtProductName.setMinimumSize(new Dimension(120,20));
        txtProductName.setPreferredSize(new Dimension(120,20));

        lblProductQuantity = new JLabel("Product quantity: ");
        lblProductQuantity.setMinimumSize(new Dimension(120,20));
        lblProductQuantity.setPreferredSize(new Dimension(120,20));
        lblProductQuantity.setForeground(Color.LIGHT_GRAY);

        txtProductQuantity = new JTextField();
        txtProductQuantity.setMinimumSize(new Dimension(120,20));
        txtProductQuantity.setPreferredSize(new Dimension(120,20));

        lblProductBasePrice = new JLabel("Product base price: ");
        lblProductBasePrice.setMinimumSize(new Dimension(120,20));
        lblProductBasePrice.setPreferredSize(new Dimension(120,20));
        lblProductBasePrice.setForeground(Color.LIGHT_GRAY);

        txtProductBasePrice = new JTextField();
        txtProductBasePrice.setMinimumSize(new Dimension(120,20));
        txtProductBasePrice.setPreferredSize(new Dimension(120,20));

        lblProductSupplier = new JLabel("Product supplier: ");
        lblProductSupplier.setMinimumSize(new Dimension(120,20));
        lblProductSupplier.setPreferredSize(new Dimension(120,20));
        lblProductSupplier.setForeground(Color.LIGHT_GRAY);

        cmbBoxSupplier = new JComboBox<>();
        for(int i = 0; i < controller.getSuppliers().size(); i++){
            cmbBoxSupplier.addItem(controller.getSuppliers().get(i));
        }
        cmbBoxSupplier.setMinimumSize(new Dimension(120,20));
        cmbBoxSupplier.setPreferredSize(new Dimension(120,20));

        lblDiscounts = new JLabel("Discount type: ");
        lblDiscounts.setMinimumSize(new Dimension(120,20));
        lblDiscounts.setPreferredSize(new Dimension(120,20));
        lblDiscounts.setForeground(Color.LIGHT_GRAY);

        btnAddProduct = new JButton("Add product");

        btnExit = new JButton("Exit");

        GRAY_BACKGROUND_COLOR = Color.decode("#2b2b2b");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setBackground(GRAY_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(500,400));
        setMinimumSize(new Dimension(500,400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblProductName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtProductName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblProductQuantity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtProductQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblProductBasePrice, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtProductBasePrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblProductSupplier, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cmbBoxSupplier, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnAddProduct, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners(){
        btnAddProduct.addActionListener(new BtnAddProductListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addProductFrame.dispose();
        }
    }

    private class BtnAddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = txtProductName.getText();
            int productQuantity = Integer.parseInt(txtProductQuantity.getText());
            int productPrice = Integer.parseInt(txtProductBasePrice.getText());
            int indexSupplier = cmbBoxSupplier.getSelectedIndex();
            String productSupplier = String.valueOf(cmbBoxSupplier.getItemAt(indexSupplier));


            if(!productName.isEmpty() && productQuantity > -1 && productPrice > -1){
                controller.sendProductInformation(productName, productQuantity, productPrice, productSupplier);
                controller.updateProductList();
            }
            else {
                JOptionPane.showMessageDialog(null, "Enter all credentials!");
            }
        }
    }
}
