package GUI.UserPanels;

import Controller.MainController;
import Entities.ComboBoxItem;
import Entities.ListItem;
import Entities.OrderItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

public class UserStorePanel extends JPanel {
    private UserMainPanel userMainPanel;
    private MainController controller;
    private JList listProducts;
    private DefaultListModel<ListItem> defaultListModel;
    private JScrollPane scrollPane;

    int productsAdded;

    private JComboBox<String> cmbChoice;

    private JLabel lblWelcome;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnRemove;
    private JButton btnAddProduct;

    private Vector<OrderItem> orderItems;


    public UserStorePanel(UserMainPanel userMainPanel, MainController controller){
        this.userMainPanel = userMainPanel;
        this.controller = controller;
        productsAdded = 0;
        orderItems = new Vector<OrderItem>();

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        txtSearch = new JTextField();
        txtSearch.setForeground(Color.BLACK);
        txtSearch.setSize(new Dimension(200, 25));
        txtSearch.setPreferredSize(new Dimension(200, 25));
        txtSearch.setMinimumSize(new Dimension(200, 25));
        txtSearch.setToolTipText("Search for products by code, name or supplier...");

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        model.addElement("Show all products");
        model.addElement("Search for product");
        model.addElement("Show all discounts");
        model.addElement("View my orders");
        model.addElement("Show by product id");
        model.addElement("Show products by supplier");
        cmbChoice = new JComboBox<>(model);
        cmbChoice.setSize(new Dimension(200, 25));
        cmbChoice.setPreferredSize(new Dimension(200, 25));
        cmbChoice.setMinimumSize(new Dimension(200, 25));

        lblWelcome = new JLabel("Welcome, ");
        lblWelcome.setFont(new Font("Helvetica", Font.BOLD, 24));
        lblWelcome.setForeground(Color.LIGHT_GRAY);

        defaultListModel = new DefaultListModel<>();

        listProducts = new JList<>(defaultListModel);
        listProducts.setSize(new Dimension(900, 300));
        listProducts.setPreferredSize(new Dimension(900, 300));
        listProducts.setMinimumSize(new Dimension(900, 300));

        scrollPane = new JScrollPane(listProducts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(500, 300));
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setMinimumSize(new Dimension(500, 300));

        btnSearch = new JButton("Search");
        btnSearch.setSize(new Dimension(100, 25));
        btnSearch.setPreferredSize(new Dimension(100, 25));
        btnSearch.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnSearch.setOpaque(true);
        btnSearch.setBorderPainted(false);
        btnSearch.setBackground(Color.decode("#518A3D"));

        btnRemove = new JButton("Remove order");
        btnRemove.setSize(new Dimension(200, 25));
        btnRemove.setPreferredSize(new Dimension(100, 25));
        btnRemove.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnRemove.setOpaque(true);
        btnRemove.setBorderPainted(false);
        btnRemove.setBackground(Color.decode("#518A3D"));

        btnAddProduct = new JButton("Add product");
        btnAddProduct.setSize(new Dimension(200, 25));
        btnAddProduct.setPreferredSize(new Dimension(100, 25));
        btnAddProduct.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddProduct.setOpaque(true);
        btnAddProduct.setBorderPainted(false);
        btnAddProduct.setBackground(Color.decode("#518A3D"));
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,550));
        setMaximumSize(new Dimension(600,550));
        setMinimumSize(new Dimension(600,550));
        setBackground(Color.decode("#2e2e2e"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 10, 0);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(lblWelcome, gbc);


        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(txtSearch, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(cmbChoice, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(btnSearch, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(btnRemove, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(btnAddProduct, gbc);
    }

    public void updateFirstname(String userName){
        lblWelcome.setText("Welcome, " + userName + "!");
    }

    public void updateOrderList(Vector<ListItem> items){
        defaultListModel.removeAllElements();

        if(items.isEmpty()) {
            defaultListModel.addElement(new ListItem("No products found", -1, -1));
        } else {
            for(int i = 0; i < items.size(); i++){
                defaultListModel.addElement(items.get(i));
            }
        }
    }


    public void updateProductList(Vector<ListItem> items){
        defaultListModel.removeAllElements();
        if(items.isEmpty()) {
            defaultListModel.addElement(new ListItem("No products found", -1, -1));
        } else {
            for(int i = 0; i < items.size(); i++){
                defaultListModel.addElement(items.get(i));
            }
        }
    }

    private void registerListeners(){
        btnSearch.addActionListener(new BtnSearchActionListener());
        btnRemove.addActionListener(new BtnRemoveActionListener());
        btnAddProduct.addActionListener(new BtnAddProductListener());
    }


    private class BtnSearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = cmbChoice.getSelectedIndex();
            if(index == 0) {
                updateProductList(controller.getAllProducts());
            } else if(index == 1) {
                updateProductList(controller.getProductsByName(txtSearch.getText()));
            } else if(index == 2) {
                updateProductList(controller.getAllDiscountedProducts());
            } else if(index == 3) {
                updateOrderList(controller.getAllOrders());
            } else if(index == 4) {
                updateProductList(controller.getProductsById(txtSearch.getText()));
            } else {
                updateProductList(controller.getProductsBySupplier(txtSearch.getText()));
            }
        }


    }

    private class BtnRemoveActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listProducts.getSelectedIndex();
            if(defaultListModel.get(index).getItemText().contains("PENDING")) {
                controller.removeOrder(defaultListModel.get(index).getItemValue());
                if(cmbChoice.getSelectedIndex() == 3) {
                    updateProductList(controller.getAllOrders());
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is not a pending order!");
            }
        }
    }


    private class BtnAddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int nbrOf = Integer.parseInt(JOptionPane.showInputDialog(null, "How many would you like to buy?"));
            int index = listProducts.getSelectedIndex();
            int indexOf = defaultListModel.getElementAt(index).getItemText().indexOf('|');
            String name = defaultListModel.getElementAt(index).getItemText().substring(0, indexOf);
            boolean exists = false;
            int existedIndex = -1;

            //If order items is not empty
            if(!orderItems.isEmpty()) {

                //Check if item exists already
                for(int i = 0; i < orderItems.size(); i++) {
                    if(orderItems.get(i).getProductName().equals(name)) {
                        exists = true;
                        existedIndex = i;
                    }
                }

                //If exists sum a new quantity, else just add the item
                if(exists) {
                    orderItems.get(existedIndex).setQuantity(orderItems.get(existedIndex).getQuantity() + nbrOf);
                } else {
                    orderItems.add(new OrderItem(defaultListModel.getElementAt(index).getItemText().substring(0, indexOf),
                            defaultListModel.getElementAt(index).getItemValue(), nbrOf,defaultListModel.getElementAt(index).getItemPrice()));
                }
            } else {
                //If it's empty just add the item
                orderItems.add(new OrderItem(defaultListModel.getElementAt(index).getItemText().substring(0, indexOf),
                        defaultListModel.getElementAt(index).getItemValue(), nbrOf,defaultListModel.getElementAt(index).getItemPrice()));
            }

            int stockNotAvailable = 0;
            //if there is an order for this session
            if(userMainPanel.getOrderId() != 0) {
                //Only update the items
                //If stock already exists, update, else add
                for(int i = 0; i < orderItems.size(); i++) {
                    if(controller.isProductAvailable(orderItems.get(i).getProductId(), orderItems.get(i).getQuantity())) {
                        if(controller.stockEntryExists(orderItems.get(i).getProductId(), userMainPanel.getOrderId())) {
                            controller.updateStock(orderItems.get(i).getProductId(), userMainPanel.getOrderId(), -orderItems.get(i).getQuantity());
                        } else {
                            controller.addStock(orderItems.get(i).getProductId(), userMainPanel.getOrderId(), -orderItems.get(i).getQuantity());
                        }
                    } else {
                        stockNotAvailable = -1;
                        orderItems.get(i).setQuantity(orderItems.get(i).getQuantity() - nbrOf);
                    }

                }

            } else {
                //If there is no order for this session
                int orderId = controller.createCustomerOrder();
                userMainPanel.setOrderId(orderId);

                //Add items
                for(int i = 0; i < orderItems.size(); i++) {
                    if(controller.isProductAvailable(orderItems.get(i).getProductId(), orderItems.get(i).getQuantity())) {
                        controller.reserveProduct(orderId, orderItems.get(i).getProductId(), -orderItems.get(i).getQuantity());
                    } else {
                        stockNotAvailable = -1;
                    }
                }
            }


            if(stockNotAvailable == -1) {
                JOptionPane.showMessageDialog(null, "There is not enough in stock");
            }

            userMainPanel.updateOrderItems(orderItems);
        }

    }
}
