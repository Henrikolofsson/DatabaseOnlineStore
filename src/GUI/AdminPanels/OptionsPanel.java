package GUI.AdminPanels;


import GUI.ApplicationMainPanel;
import GUI.LogInPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OptionsPanel extends JPanel {
    private AdminMainPanel adminMainPanel;

    private JLabel lblAddSupplier;
    private JLabel lblAddProduct;
    private JLabel lblAddDiscount;
    private JLabel lblDeleteProduct;
    private JLabel lblHandleProduct;
    private JLabel lblHandleOrders;


    public OptionsPanel(AdminMainPanel adminMainPanel){
        this.adminMainPanel = adminMainPanel;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents(){
        lblAddSupplier = new JLabel();
        lblAddSupplier.setText("Add supplier");
        lblAddSupplier.setSize(new Dimension(100, 50));
        lblAddSupplier.setPreferredSize(new Dimension(100, 50));
        lblAddSupplier.setFont(new Font("Helvetica", Font.BOLD, 12));
        lblAddSupplier.setForeground(Color.LIGHT_GRAY);

        lblAddProduct = new JLabel();
        lblAddProduct.setText("Add product");
        lblAddProduct.setSize(new Dimension(100, 50));
        lblAddProduct.setPreferredSize(new Dimension(100, 50));
        lblAddProduct.setFont(new Font("Helvetica", Font.BOLD, 12));
        lblAddProduct.setForeground(Color.LIGHT_GRAY);

        lblAddDiscount = new JLabel();
        lblAddDiscount.setText("Add discount");
        lblAddDiscount.setSize(new Dimension(100, 50));
        lblAddDiscount.setPreferredSize(new Dimension(100, 50));
        lblAddDiscount.setFont(new Font("Helvetica", Font.BOLD, 12));
        lblAddDiscount.setForeground(Color.LIGHT_GRAY);

        lblDeleteProduct = new JLabel();
        lblDeleteProduct.setText("Delete product");
        lblDeleteProduct.setSize(new Dimension(100, 50));
        lblDeleteProduct.setPreferredSize(new Dimension(100, 50));
        lblDeleteProduct.setFont(new Font("Helvetica", Font.BOLD, 12));
        lblDeleteProduct.setForeground(Color.LIGHT_GRAY);

        lblHandleProduct = new JLabel();
        lblHandleProduct.setText("Handle product");
        lblHandleProduct.setSize(new Dimension(100, 50));
        lblHandleProduct.setPreferredSize(new Dimension(100, 50));
        lblHandleProduct.setFont(new Font("Helvetica", Font.BOLD, 12));
        lblHandleProduct.setForeground(Color.LIGHT_GRAY);

        lblHandleOrders = new JLabel();
        lblHandleOrders.setText("Handle orders");
        lblHandleOrders.setSize(new Dimension(100, 50));
        lblHandleOrders.setPreferredSize(new Dimension(100, 50));
        lblHandleOrders.setFont(new Font("Helvetica", Font.BOLD, 12));
        lblHandleOrders.setForeground(Color.LIGHT_GRAY);
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,50));
        setMaximumSize(new Dimension(600,50));
        setMinimumSize(new Dimension(600,50));
        setBackground(Color.decode("#4F4F4F"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(lblAddSupplier, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(lblAddProduct, gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        add(lblDeleteProduct, gbc);

        gbc.gridy = 0;
        gbc.gridx = 3;
        add(lblAddDiscount, gbc);

        gbc.gridy = 0;
        gbc.gridx = 4;
        add(lblHandleProduct, gbc);

        gbc.gridy = 0;
        gbc.gridx = 5;
        add(lblHandleOrders, gbc);
    }

    private void registerListeners() {
        lblAddProduct.addMouseListener(new ProductLabelListener());
        lblAddSupplier.addMouseListener(new SupplierLabelListener());
        lblAddDiscount.addMouseListener(new DiscountLabelListener());
        lblDeleteProduct.addMouseListener(new DeleteProductLabelListener());
        lblHandleProduct.addMouseListener(new HandleProductLabelListener());
        lblHandleOrders.addMouseListener(new HandleOrderLabelListener());
    }

    private class HandleOrderLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            //OPEN HANDLE ORDERS WINDOW
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblHandleOrders.setText("<HTML><U>Handle orders</U></HTML>");
            lblHandleOrders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblHandleOrders.setText("Handle orders");
            lblHandleOrders.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private class DiscountLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            adminMainPanel.openAddDiscountFrame();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblAddDiscount.setText("<HTML><U>Add discount</U></HTML>");
            lblAddDiscount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblAddDiscount.setText("Add discount");
            lblAddDiscount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private class DeleteProductLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            adminMainPanel.openDeleteProductFrame();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblDeleteProduct.setText("<HTML><U>Delete product</U></HTML>");
            lblDeleteProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblDeleteProduct.setText("Delete product");
            lblDeleteProduct.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private class ProductLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            adminMainPanel.openAddProductFrame();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblAddProduct.setText("<HTML><U>Add product</U></HTML>");
            lblAddProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblAddProduct.setText("Add product");
            lblAddProduct.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private class HandleProductLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            adminMainPanel.openHandleProductFrame();

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblHandleProduct.setText("<HTML><U>Handle product</U></HTML>");
            lblHandleProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblHandleProduct.setText("Handle product");
            lblHandleProduct.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private class SupplierLabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            adminMainPanel.openAddSupplierFrame();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblAddSupplier.setText("<HTML><U>Add supplier</U></HTML>");
            lblAddSupplier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            lblAddSupplier.setText("Add supplier");
            lblAddSupplier.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
