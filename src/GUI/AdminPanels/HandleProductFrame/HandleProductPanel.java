package GUI.AdminPanels.HandleProductFrame;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HandleProductPanel extends JPanel {
    private MainController controller;
    private HandleProductFrame handleProductFrame;

    private JLabel lblProductToEdit;

    private JLabel lblNewQuantity;
    private JLabel lblAddDiscountStart;
    private JLabel lblAddDiscountEnd;
    private JLabel lblUnusedDiscounts;
    private JLabel lblUsedDiscounts;

    private JTextField txtNewQuantity;
    private JTextField txtAddDiscountStart;
    private JTextField txtAddDiscountEnd;

    private JComboBox<String> cmbBoxProducts;
    private JComboBox<String> cmbBoxUnusedDiscounts;
    private JComboBox<String> cmbBoxUsedDiscounts;

    private JButton btnEditQuantity;
    private JButton btnAddUnusedDiscount;
    private JButton btnAddUsedDiscount;
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
        lblProductToEdit = new JLabel("<html><div style='text-align: center;'>Select a product to edit</div></html>");
        lblProductToEdit.setMinimumSize(new Dimension(140,20));
        lblProductToEdit.setPreferredSize(new Dimension(140,20));
        lblProductToEdit.setForeground(Color.LIGHT_GRAY);

        cmbBoxProducts = new JComboBox<>();
        for(int i = 0; i < controller.getProducts().size(); i++){
            cmbBoxProducts.addItem(controller.getProducts().get(i));
        }

        lblNewQuantity = new JLabel("New Quantity: ");
        lblNewQuantity.setMinimumSize(new Dimension(120,20));
        lblNewQuantity.setPreferredSize(new Dimension(120,20));
        lblNewQuantity.setForeground(Color.LIGHT_GRAY);

        lblUnusedDiscounts = new JLabel("Unused discounts:");
        lblUnusedDiscounts.setMinimumSize(new Dimension(120,20));
        lblUnusedDiscounts.setPreferredSize(new Dimension(120,20));
        lblUnusedDiscounts.setForeground(Color.LIGHT_GRAY);

        cmbBoxUnusedDiscounts = new JComboBox<>();
        for(int i = 0; i < controller.getDiscounts().size(); i++){
            cmbBoxUnusedDiscounts.addItem(controller.getDiscounts().get(i));
        }

        lblUsedDiscounts = new JLabel("Used discounts:");
        lblUsedDiscounts.setMinimumSize(new Dimension(120,20));
        lblUsedDiscounts.setPreferredSize(new Dimension(120,20));
        lblUsedDiscounts.setForeground(Color.LIGHT_GRAY);

        cmbBoxUsedDiscounts = new JComboBox<>();
        for(int i = 0; i < controller.getDiscounts().size(); i++){
            cmbBoxUsedDiscounts.addItem("TO BE ADDED");
        }

        lblAddDiscountStart = new JLabel("Discount startdate:");
        lblAddDiscountStart.setMinimumSize(new Dimension(120,20));
        lblAddDiscountStart.setPreferredSize(new Dimension(120,20));
        lblAddDiscountStart.setForeground(Color.LIGHT_GRAY);

        lblAddDiscountEnd = new JLabel("Discount startdate:");
        lblAddDiscountEnd.setMinimumSize(new Dimension(120,20));
        lblAddDiscountEnd.setPreferredSize(new Dimension(120,20));
        lblAddDiscountEnd.setForeground(Color.LIGHT_GRAY);

        txtAddDiscountStart = new JTextField();
        txtAddDiscountStart.setSize(new Dimension(100, 20));
        txtAddDiscountStart.setPreferredSize(new Dimension(100, 20));
        txtAddDiscountStart.setText("YYYY-MM-DD");

        txtAddDiscountEnd = new JTextField();
        txtAddDiscountEnd.setSize(new Dimension(100, 20));
        txtAddDiscountEnd.setPreferredSize(new Dimension(100, 20));
        txtAddDiscountEnd.setText("YYYY-MM-DD");

        txtNewQuantity = new JTextField();
        txtNewQuantity.setSize(new Dimension(50, 20));
        txtNewQuantity.setPreferredSize(new Dimension(50, 20));

        btnEditQuantity = new JButton("Edit quantity");
        btnEditQuantity.setSize(new Dimension(100, 25));
        btnEditQuantity.setPreferredSize(new Dimension(100, 25));
        btnEditQuantity.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnEditQuantity.setOpaque(true);
        btnEditQuantity.setBorderPainted(false);
        btnEditQuantity.setBackground(Color.decode("#518A3D"));

        btnAddUnusedDiscount = new JButton("Add discount period");
        btnAddUnusedDiscount.setSize(new Dimension(200, 25));
        btnAddUnusedDiscount.setPreferredSize(new Dimension(200, 25));
        btnAddUnusedDiscount.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddUnusedDiscount.setOpaque(true);
        btnAddUnusedDiscount.setBorderPainted(false);
        btnAddUnusedDiscount.setBackground(Color.decode("#518A3D"));

        btnAddUsedDiscount = new JButton("Update and add discount");
        btnAddUsedDiscount.setSize(new Dimension(200, 25));
        btnAddUsedDiscount.setPreferredSize(new Dimension(200, 25));
        btnAddUsedDiscount.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddUsedDiscount.setOpaque(true);
        btnAddUsedDiscount.setBorderPainted(false);
        btnAddUsedDiscount.setBackground(Color.decode("#518A3D"));

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(100, 25));
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
        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(600,600));
        setMinimumSize(new Dimension(600,600));

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
        gbc.gridwidth = 1;
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
        gbc.gridwidth = 1;
        add(lblUnusedDiscounts, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cmbBoxUnusedDiscounts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblUsedDiscounts, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(cmbBoxUsedDiscounts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblAddDiscountStart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtAddDiscountStart, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblAddDiscountEnd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        add(txtAddDiscountEnd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(btnAddUnusedDiscount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(btnAddUsedDiscount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnEditQuantity.addActionListener(new BtnEditQuantityListener());
        btnAddUnusedDiscount.addActionListener(new BtnAddUnusedDiscountPeriodListener());
        btnExit.addActionListener(new BtnExitListener());
        txtAddDiscountStart.addMouseListener(new TxtStartDateListener());
        txtAddDiscountEnd.addMouseListener(new TxtEndDateListener());
    }

    private class TxtStartDateListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            txtAddDiscountStart.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(txtAddDiscountStart.getText().isEmpty()){
                txtAddDiscountStart.setText("YYYY-MM-DD");
            }
        }
    }

    private class TxtEndDateListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            txtAddDiscountEnd.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(txtAddDiscountEnd.getText().isEmpty()){
                txtAddDiscountEnd.setText("YYYY-MM-DD");
            }

        }
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

    private class BtnAddUnusedDiscountPeriodListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String startDate = txtAddDiscountStart.getText();
            String endDate = txtAddDiscountEnd.getText();
            int index = cmbBoxProducts.getSelectedIndex();
            String productNameToUpdate = String.valueOf(cmbBoxProducts.getItemAt(index));

            int indexDiscount = cmbBoxUnusedDiscounts.getSelectedIndex();
            String discountToSetDate = String.valueOf(cmbBoxUnusedDiscounts.getItemAt(indexDiscount));

            controller.sendToAddDiscountPeriod(startDate, endDate, productNameToUpdate, discountToSetDate);
        }
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleProductFrame.dispose();
        }
    }
}

