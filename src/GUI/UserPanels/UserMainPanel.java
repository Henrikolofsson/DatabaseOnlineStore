package GUI.UserPanels;

import GUI.ApplicationMainPanel;
import GUI.UserPanels.ShoppingCartFrame.ShoppingCartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserMainPanel extends JPanel {
    private ApplicationMainPanel applicationMainPanel;
    private UserOptionsPanel optionsPanel;
    private UserStorePanel userStorePanel;

    public UserMainPanel(ApplicationMainPanel applicationMainPanel){
        this.applicationMainPanel = applicationMainPanel;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents(){
        this.optionsPanel = new UserOptionsPanel(this);
        this.userStorePanel = new UserStorePanel(this);
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

    public String getFirstname() {
        String usernameLogin = applicationMainPanel.getUsernameLogin();
        String usernamePassword = applicationMainPanel.getPasswordLogin();
        return applicationMainPanel.getFirstname(usernameLogin, usernamePassword);
    }

    public void updateFirstname() {
        userStorePanel.updateFirstname();
    }

    public boolean checkQuantity(int nbrOfItems, int productID) {
        return applicationMainPanel.checkQuantity(nbrOfItems, productID);
    }

    public void updateShoppingCartBtn(int productsAdded) {
        optionsPanel.updateShoppingCartBtn(productsAdded);
    }

    public void getOrderedProducts(int productID, int nbrOfItems) {

    }

    public void openShoppingCart() {
        applicationMainPanel.openShoppingcart();
    }

    public ArrayList<String> getProductsForCustomers() {
        return applicationMainPanel.getProductsForCustomers();
    }
}
