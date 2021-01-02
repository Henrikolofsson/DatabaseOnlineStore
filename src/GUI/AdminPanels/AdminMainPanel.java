package GUI.AdminPanels;

import Entities.Admin;
import GUI.ApplicationMainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminMainPanel extends JPanel {
    private ApplicationMainPanel applicationMainPanel;
    private OptionsPanel optionsPanel;
    private AdminStorePanel adminStorePanel;

    public AdminMainPanel(ApplicationMainPanel applicationMainPanel){
        this.applicationMainPanel = applicationMainPanel;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents(){
        this.optionsPanel = new OptionsPanel(this);
        this.adminStorePanel = new AdminStorePanel(this);
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
        add(adminStorePanel, gbc);
    }

    public void openAddSupplierFrame() {
        applicationMainPanel.openAddSupplierFrame();
    }

    public void openAddProductFrame() {
        applicationMainPanel.openAddProductFrame();
    }

    public void openAddDiscountFrame() {
        applicationMainPanel.openAddDiscountFrame();
    }

    public void openDeleteProductFrame() {
        applicationMainPanel.openDeleteProductFrame();
    }

    public ArrayList<String> getAllProducts(){
        return applicationMainPanel.getAllProducts();
    }

    public void updateProductList() {
        adminStorePanel.updateProductList();
    }

    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
        return applicationMainPanel.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
    }

    public void openHandleProductFrame() {
        applicationMainPanel.openHandleProductFrame();
    }

    public String getFirstname() {
        String usernameLogin = applicationMainPanel.getUsernameLogin();
        String usernamePassword = applicationMainPanel.getPasswordLogin();
        return applicationMainPanel.getFirstname(usernameLogin, usernamePassword);
    }

    public void updateFirstName() {
        adminStorePanel.updateFirstname();
    }
}
