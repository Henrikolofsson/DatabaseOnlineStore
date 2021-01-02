package GUI.AdminPanels.HandleProductFrame;

import Controller.MainController;
import GUI.AdminPanels.DeleteProductFrame.DeleteProductPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleProductPanel extends JPanel {
    private MainController controller;
    private HandleProductFrame handleProductFrame;

    private JLabel lblProductToEdit;

    private JLabel lblNewQuantity;
    private JLabel lblAddDiscountPeriod;
    private JLabel lblDateTemp;

    private JTextField txtNewQuantity;

    private JComboBox<String> cmbBoxProducts;

    private JButton btnEditQuantity;
    private JButton btnAddDiscountPeriod;
    private JButton btnExit;

    private Color GRAY_BACKGROUND_COLOR;

    public HandleProductPanel(MainController controller, HandleProductFrame handleProductFrame){
        this.controller = controller;
        this.handleProductFrame = handleProductFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductToEdit = new JLabel("Select product to edit");
        lblProductToEdit.setMinimumSize(new Dimension(120,20));
        lblProductToEdit.setPreferredSize(new Dimension(120,20));
        lblProductToEdit.setForeground(Color.LIGHT_GRAY);

        cmbBoxProducts = new JComboBox<>();
        for(int i = 0; i < controller.getProducts().size(); i++){
            cmbBoxProducts.addItem(controller.getProducts().get(i));
        }

        lblNewQuantity = new JLabel("New Quantity: ");
        lblNewQuantity.setMinimumSize(new Dimension(120,20));
        lblNewQuantity.setPreferredSize(new Dimension(120,20));
        lblNewQuantity.setForeground(Color.LIGHT_GRAY);

        lblAddDiscountPeriod = new JLabel("Discount period: ");
        lblAddDiscountPeriod.setMinimumSize(new Dimension(120,20));
        lblAddDiscountPeriod.setPreferredSize(new Dimension(120,20));
        lblAddDiscountPeriod.setForeground(Color.LIGHT_GRAY);

        lblDateTemp = new JLabel("Temporary DateSelector");
        lblDateTemp.setMinimumSize(new Dimension(120,20));
        lblDateTemp.setPreferredSize(new Dimension(120,20));
        lblDateTemp.setForeground(Color.LIGHT_GRAY);

        txtNewQuantity = new JTextField();
        txtNewQuantity.setSize(new Dimension(50, 20));
        txtNewQuantity.setPreferredSize(new Dimension(50, 20));

        btnEditQuantity = new JButton("Edit quantity");

        btnAddDiscountPeriod = new JButton("Add discount period");

        btnExit = new JButton("Exit");



        GRAY_BACKGROUND_COLOR = Color.decode("#2b2b2b");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setBackground(GRAY_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(600,400));
        setMinimumSize(new Dimension(600,400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblProductToEdit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(cmbBoxProducts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblNewQuantity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtNewQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnEditQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblAddDiscountPeriod, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(lblDateTemp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnAddDiscountPeriod, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnExit, gbc);

    }

    private void registerListeners() {
        btnEditQuantity.addActionListener(new BtnEditQuantityListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnEditQuantityListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String strNewQuantity = txtNewQuantity.getText();
            int newQuantity = 0;
            int index = cmbBoxProducts.getSelectedIndex();
            String productNameToUpdate = String.valueOf(cmbBoxProducts.getItemAt(index));

            boolean isParsable;
            try {
                newQuantity = Integer.parseInt(strNewQuantity);
                isParsable = true;
            } catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Enter a number!");
                n.printStackTrace();
                isParsable = false;
            }
            if(isParsable){
                controller.sendToUpdateQuantity(newQuantity, productNameToUpdate);
            }

        }
    }

    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            handleProductFrame.dispose();
        }
    }
}

