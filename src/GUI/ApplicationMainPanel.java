package GUI;

import Controller.MainController;
import GUI.AdminPanels.AdminMainPanel;
import GUI.UserPanels.UserMainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ApplicationMainPanel extends JPanel {
    private MainController controller;
    private LogInPanel pnlLogIn;
    private StorePanel pnlStore;
    private AdminMainPanel pnlAdminMain;
    private UserMainPanel pnlUserMain;


    public ApplicationMainPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        pnlLogIn = new LogInPanel(this);
        pnlStore = new StorePanel(this);
        pnlAdminMain = new AdminMainPanel(this);
        pnlUserMain = new UserMainPanel(this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#2b2b2b"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlLogIn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pnlStore, gbc);
    }

    public void updateAdminView() {
        remove(pnlLogIn);
        remove(pnlStore);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#2b2b2b"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlAdminMain, gbc);

        revalidate();
        repaint();
    }

    public void updateUserView() {
        remove(pnlLogIn);
        remove(pnlStore);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,800));
        setMaximumSize(new Dimension(600,800));
        setMinimumSize(new Dimension(600,800));
        setBackground(Color.decode("#2b2b2b"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlUserMain, gbc);

        revalidate();
        repaint();
    }



    public boolean isUserNormal(String userNameLogin, String passwordLogin) {
        controller.isUserNormal(userNameLogin, passwordLogin);
        return controller.isUserNormal(userNameLogin, passwordLogin);
    }

    public void openCreateAccountWindow() {
        controller.openCreateAccountWindow();
    }

    public void retrieveFirstName(String userNameLogin) {
        controller.retrieveFirstName(userNameLogin);
    }

    public boolean isUserAdmin(String userNameLogin, String passwordLogin) {
        controller.isUserAdmin(userNameLogin, passwordLogin);
        return controller.isUserAdmin(userNameLogin, passwordLogin);
    }

    public void openAddSupplierFrame() {
        controller.openAddSupplierFrame();
    }

    public void openAddProductFrame() {
        controller.openAddProductFrame();
    }

    public void openAddDiscountFrame() {
        controller.openAddDiscountFrame();
    }

    public void openDeleteProductFrame() {
        controller.openDeleteProductFrame();
    }

    public ArrayList<String> getAllProducts() {
        return controller.getAllProducts();
    }

    public void updateProductList() {
        pnlAdminMain.updateProductList();
    }

    public void searchProducts() {
        controller.searchProducts();
    }

    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
        return controller.getSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
    }

    public void openHandleProductFrame() {
        controller.openHandleProductFrame();
    }

    public String getFirstname(String usernameLogin, String passwordLogin) {
        return controller.getFirstName(usernameLogin, passwordLogin);
    }

    public String getUsernameLogin() {
        return pnlLogIn.getUsernameLogin();
    }

    public String getPasswordLogin() {
        return pnlLogIn.getPasswordLogin();
    }

    public void updateUserFirstName() {
        pnlUserMain.updateFirstname();
    }

    public void updateAdminFirstName() {
        pnlAdminMain.updateFirstName();
    }

    public void openViewUsedDiscountsFrame() {
        controller.openViewUsedDiscountsFrame();
    }

    public void updateUsedDiscounts() {
        controller.updateUsedDiscounts();
    }

    public void setAdminViewTitle() {
        controller.setAdminViewTitle();
    }

    public void setUserViewTitle() {
        controller.setUserViewTitle();
    }

    public ArrayList<String> getAllProductsUser() {
        return controller.getAllProductsUser();
    }

    public boolean checkQuantity(int nbrOfItems, int productID) {
        return controller.checkQuantity(nbrOfItems, productID);
    }

    public void openShoppingcart() {
        controller.openShoppingCart();
    }

    public void getOrderedProducts(int productID, int nbrOfItems) {
        controller.getProductsOrdered(productID, nbrOfItems);
    }

    public void openHandleOrdersFrame() {
        controller.openHandleOrdersFrame();
    }

    public ArrayList<String> getProductsForCustomers() {
        return controller.getProductsForCustomers();
    }
}
