package GUI;

import Controller.MainController;
import Entities.ListItem;
import GUI.AdminPanels.AdminMainPanel;
import GUI.AdminPanels.AdminStorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StorePanel extends JPanel {
    private ApplicationMainPanel applicationMainPanel;
    private MainController controller;
    private JList<ListItem> listProducts;
    private DefaultListModel<ListItem> defaultListModel;
    private JScrollPane scrollPane;

    private JLabel lblWelcome;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnUpdate;

    public StorePanel(ApplicationMainPanel applicationMainPanel, MainController controller) {
        this.applicationMainPanel = applicationMainPanel;
        this.controller = controller;
        initializeComponents();
        updateProductList(controller.getAllProducts());
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

        lblWelcome = new JLabel("Welcome, Log in to start shopping!");
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
    }

    public void updateProductList(Vector<ListItem> products){
        defaultListModel.removeAllElements();
        for(int i = 0; i < products.size(); i++){
            defaultListModel.addElement(products.get(i));
        }
    }

    private void registerListeners(){
        btnSearch.addActionListener(new BtnSearchActionListener());
        btnUpdate.addActionListener(new BtnUpdateActionListener());
    }

    public static boolean isParsable(String searchedCode) {
        try {
            Integer.parseInt(searchedCode);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
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
        gbc.gridwidth = 2;
        add(btnUpdate, gbc);

    }



    private class BtnSearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(txtSearch.getText().isEmpty()) {
                updateProductList(controller.getAllProducts());
            } else {
                updateProductList(controller.getProductsByName(txtSearch.getText()));
            }
        }
    }

    private class BtnUpdateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
