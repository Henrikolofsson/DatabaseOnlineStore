package GUI.UserPanels;

import Controller.MainController;
import GUI.ApplicationMainPanel;
import GUI.UserPanels.ShoppingCartFrame.ShoppingCartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserMainPanel extends JPanel {
    private ApplicationMainPanel applicationMainPanel;
    private MainController controller;
    private UserOptionsPanel optionsPanel;
    private UserStorePanel userStorePanel;

    public UserMainPanel(ApplicationMainPanel applicationMainPanel, MainController controller){
        this.applicationMainPanel = applicationMainPanel;
        this.controller = controller;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents(){
        this.optionsPanel = new UserOptionsPanel(this);
        this.userStorePanel = new UserStorePanel(this, controller);
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#3b3b3b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(optionsPanel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(userStorePanel, gbc);
    }

    public ArrayList<String> getAllProducts() {
        return applicationMainPanel.getAllProducts();
    }

    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
        return applicationMainPanel.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
    }

    public void updateFirstname(String userName) {
        userStorePanel.updateFirstname(userName);
    }


    public void updateShoppingCartBtn(int productsAdded) {
        optionsPanel.updateShoppingCartBtn(productsAdded);
    }

    public void getOrderedProducts(int productID, int nbrOfItems) {

    }


    public ArrayList<String> getProductsForCustomers() {
        return applicationMainPanel.getProductsForCustomers();
    }
}
