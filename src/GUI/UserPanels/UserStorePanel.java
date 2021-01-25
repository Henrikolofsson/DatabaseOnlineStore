package GUI.UserPanels;

import Controller.MainController;
import Entities.ComboBoxItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UserStorePanel extends JPanel {
    private UserMainPanel userMainPanel;
    private MainController controller;
    private JList listProducts;
    private DefaultListModel<ComboBoxItem> defaultListModel;
    private JScrollPane scrollPane;

    int productsAdded;

    private JComboBox<String> cmbChoice;

    private JLabel lblWelcome;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnUpdate;
    private JButton btnAddProduct;


    public UserStorePanel(UserMainPanel userMainPanel, MainController controller){
        this.userMainPanel = userMainPanel;
        this.controller = controller;
        productsAdded = 0;

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

        btnUpdate = new JButton("Update List");
        btnUpdate.setSize(new Dimension(200, 25));
        btnUpdate.setPreferredSize(new Dimension(100, 25));
        btnUpdate.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnUpdate.setOpaque(true);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setBackground(Color.decode("#518A3D"));

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
        setPreferredSize(new Dimension(600,750));
        setMaximumSize(new Dimension(600,750));
        setMinimumSize(new Dimension(600,750));
        setBackground(Color.decode("#2e2e2e"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 50, 0);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(lblWelcome, gbc);


        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        add(txtSearch, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        add(cmbChoice, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        add(btnSearch, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(btnUpdate, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(btnAddProduct, gbc);
    }

    public void updateFirstname(String userName){
        lblWelcome.setText("Welcome, " + userName + "!");
    }

    public void updateProductList(Vector<ComboBoxItem> items){
        defaultListModel.removeAllElements();
        if(items.isEmpty()) {
            defaultListModel.addElement(new ComboBoxItem("No products found", -1));
        } else {
            for(int i = 0; i < items.size(); i++){
                defaultListModel.addElement(items.get(i));
            }
        }
    }

    private void registerListeners(){
        btnSearch.addActionListener(new BtnSearchActionListener());
        btnUpdate.addActionListener(new BtnUpdateActionListener());
        btnAddProduct.addActionListener(new BtnAddProductListener());
    }


    private class BtnSearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = cmbChoice.getSelectedIndex();
            if(index == 0) {
                updateProductList(controller.getAllProducts());
            } else if(index == 1) {
                controller.btnPressed("SearchForProducts");
                updateProductList(controller.getProductsByName(txtSearch.getText()));
            } else if(index == 2) {
                controller.btnPressed("GetAllDiscounts");
                updateProductList(controller.getAllDiscountedProducts());
            } else if(index == 3) {
                controller.btnPressed("ViewOrders");
            } else if(index == 4) {
                controller.btnPressed("SearchForProductsById");
                updateProductList(controller.getProductsById(txtSearch.getText()));
            } else {
                controller.btnPressed("SearchForProductsBySupplier");
            }
        }
    }

    private class BtnUpdateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private class BtnAddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
