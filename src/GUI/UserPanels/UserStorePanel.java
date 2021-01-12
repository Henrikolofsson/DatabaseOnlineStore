package GUI.UserPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserStorePanel extends JPanel {
    private UserMainPanel userMainPanel;
    private JList listProducts;
    private DefaultListModel<String> defaultListModel;
    private JScrollPane scrollPane;

    int productsAdded;

    private JLabel lblWelcome;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnUpdate;
    private JButton btnAddProduct;


    public UserStorePanel(UserMainPanel userMainPanel){
        this.userMainPanel = userMainPanel;
        productsAdded = 0;

        initializeComponents();
        updateProductList();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        txtSearch = new JTextField();
        txtSearch.setForeground(Color.BLACK);
        txtSearch.setSize(new Dimension(400, 25));
        txtSearch.setPreferredSize(new Dimension(400, 25));
        txtSearch.setMinimumSize(new Dimension(400, 25));
        txtSearch.setToolTipText("Search for products by code, name or supplier...");

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

        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(txtSearch, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(btnSearch, gbc);

        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(btnUpdate, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(btnAddProduct, gbc);
    }

    public void updateFirstname(){
        lblWelcome.setText("Welcome, " + userMainPanel.getFirstname() + "!");
    }

    public void updateProductList(){
        defaultListModel.removeAllElements();
        for(int i = 0; i < userMainPanel.getProductsForCustomers().size(); i++){
            defaultListModel.addElement(userMainPanel.getProductsForCustomers().get(i));
        }
    }

    public void updateSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct){
        defaultListModel.removeAllElements();
        for(int i = 0; i < userMainPanel.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct).size(); i++){
            defaultListModel.addElement(userMainPanel.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct).get(i));
        }
        if(defaultListModel.isEmpty()){
            defaultListModel.addElement("No products found");
        }
    }

    private void registerListeners(){
        btnSearch.addActionListener(new BtnSearchActionListener());
        btnUpdate.addActionListener(new BtnUpdateActionListener());
        btnAddProduct.addActionListener(new BtnAddProductListener());
    }

    public static boolean isParsable(String searchedCode) {
        try {
            Integer.parseInt(searchedCode);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private class BtnSearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchedCode = txtSearch.getText();
            String searchedSupplier = txtSearch.getText();
            String searchedProduct = txtSearch.getText();

            if(isParsable(searchedCode)){
                searchedProduct = null;
                searchedSupplier = null;
            }
            else {
                searchedCode = null;
            }

            System.out.println(searchedCode + searchedProduct + searchedSupplier);

            if(!txtSearch.getText().isEmpty()){
                userMainPanel.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
                updateSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
            } else {
                userMainPanel.getAllProducts();
                updateProductList();
            }
        }
    }

    private class BtnUpdateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userMainPanel.getAllProducts();
            updateProductList();
        }
    }

    private int getIdFromString(){
        if(!listProducts.isSelectionEmpty()){
            String str = String.valueOf(listProducts.getSelectedValue());
            String result = str.substring(6, str.indexOf("|")-1);
            System.out.println(result);
            return Integer.parseInt(result);
        } else {
            JOptionPane.showMessageDialog(null, "Select a product before adding!");
            return -1;
        }

    }

    private class BtnAddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(getIdFromString() > 0){
                    int nbrOfItems = Integer.parseInt(JOptionPane.showInputDialog("How many items?"));
                    int productID = getIdFromString();
                    if(userMainPanel.checkQuantity(nbrOfItems, productID)){
                        JOptionPane.showMessageDialog(null, "Product added!");
                        productsAdded++;
                        userMainPanel.updateShoppingCartBtn(productsAdded);
                        userMainPanel.getOrderedProducts(productID, nbrOfItems);
                        updateProductList();
                    }
                }
            } catch (NumberFormatException n) {
                n.printStackTrace();
                JOptionPane.showMessageDialog(null,"Enter a real number!");
            }

        }
    }
}
