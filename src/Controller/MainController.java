package Controller;

import Database.DatabaseController;
import Entities.*;
import Enums.Countries;
import GUI.AdminPanels.AddDiscountFrame.AddDiscountFrame;
import GUI.AdminPanels.AddProductFrame.AddProductFrame;
import GUI.AdminPanels.AddSupplierFrame.AddSupplierFrame;
import GUI.AdminPanels.DeleteProductFrame.DeleteProductFrame;
import GUI.AdminPanels.HandleOrders.HandleOrdersFrame;
import GUI.AdminPanels.HandleProductFrame.HandleProductFrame;
import GUI.AdminPanels.ViewUsedDiscountsFrame.ViewUsedDiscountsFrame;
import GUI.ApplicationFrame;
import GUI.ApplicationMainPanel;
import GUI.UserPanels.ShoppingCartFrame.ShoppingCartFrame;
import GUI.Usercreation.CreateAccountFrame;

import java.util.ArrayList;

public class MainController {
    private DatabaseController databaseController;
    private ApplicationFrame applicationFrame;
    private AddSupplierFrame addSupplierFrame;
    private CreateAccountFrame createAccountFrame;
    private AddProductFrame addProductFrame;
    private ApplicationMainPanel applicationMainPanel;
    private AddDiscountFrame addDiscountFrame;
    private HandleProductFrame handleProductFrame;
    private ViewUsedDiscountsFrame viewUsedDiscountsFrame;
    private ShoppingCartFrame shoppingCartFrame;
    private Login login;
    private String firstName;
    private DeleteProductFrame deleteProductFrame;
    private HandleOrdersFrame handleOrdersFrame;

    public MainController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        applicationFrame = new ApplicationFrame(this, databaseController);
        applicationMainPanel = new ApplicationMainPanel(this);
    }

    public Countries[] getCountries() {
        return Countries.values();
    }

    public void openCreateAccountWindow() {
        createAccountFrame = new CreateAccountFrame(this, databaseController);
    }

    public boolean createAdminUser(User user, String adminPassword) {
        if(databaseController.checkAdminPassword(adminPassword)) {
            databaseController.createAdminUser(user);
            createAccountFrame.dispose();
            return true;
        }
        return false;
    }

    public boolean createNormalUser(User user) {
        if(databaseController.createNormalUser(user)) {
            createAccountFrame.dispose();
            return true;
        }
        return false;
    }

    public void setLoginCredentials(String userNameLogin, String passwordLogin) {
        login = new Login(userNameLogin, passwordLogin, firstName);
    }

    public void addSupplier(String supplierName, String supplierPhone, String supplierAddress){
        Supplier supplier = new Supplier(supplierName, supplierPhone, supplierAddress, databaseController);
        supplier.addSupplier();
    }

    public void setFirstName(String firstName){
        this.firstName = login.getFirstName();
        System.out.println("Firstname in updateLoggedInLabel MainController: " + firstName);
    }

    public String getFirstName(String usernameLogin, String passwordLogin) {
        return databaseController.getFirstName(usernameLogin, passwordLogin);
    }

    public boolean isUserNormal(String userNameLogin, String passwordLogin) {
        databaseController.isUserNormal(userNameLogin, passwordLogin);
        return databaseController.isUserNormal(userNameLogin, passwordLogin);
    }

    public void retrieveFirstName(String userNameLogin) {
        databaseController.retrieveFirstName(userNameLogin);
    }

    public boolean isUserAdmin(String userNameLogin, String passwordLogin) {
        databaseController.isUserAdmin(userNameLogin, passwordLogin);
        return databaseController.isUserAdmin(userNameLogin, passwordLogin);
    }

    public void sendSupplierCredentials(String supplierName, String supplierAddress, String supplierPhone) {
        Supplier supplier = new Supplier(supplierName, supplierAddress, supplierPhone, databaseController);
        databaseController.addSupplier(supplier);
    }

    public void openAddSupplierFrame() {
        addSupplierFrame = new AddSupplierFrame(this, databaseController);
    }

    public ArrayList<String> getSuppliers() {
        return databaseController.getSuppliers();
    }

    public void openAddProductFrame() {
        addProductFrame = new AddProductFrame(this, databaseController);
    }

    public void sendProductInformation(String productName, int productQuantity, int productPrice, String productSupplier) {
        Product product = new Product(productName, productQuantity, productPrice, productSupplier, databaseController);
        databaseController.addProduct(product);
    }

    public void sendDiscountInformation(int discountCode, double discountPercentage, String discountReason) {
        Discount discount = new Discount(discountCode, discountPercentage, discountReason, databaseController);
        databaseController.addDiscount(discount);
    }

    public void openAddDiscountFrame() {
        addDiscountFrame = new AddDiscountFrame(this, databaseController);
    }

    public ArrayList<String> getDiscounts() {
        return databaseController.getDiscounts();
    }

    public ArrayList<String> getProducts() {
        return databaseController.getProducts();
    }

    public void sendToDelete(String productNameToDelete) {
        databaseController.deleteProduct(productNameToDelete);
    }

    public void openDeleteProductFrame() {
        deleteProductFrame = new DeleteProductFrame(this, databaseController);
    }

    public ArrayList<String> getAllProducts() {
        return databaseController.getAllProducts();
    }

    public void updateProductList() {
        applicationMainPanel.updateProductList();
    }

    public void searchProducts() {
    }

    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
        return databaseController.getUserSearchedProducts(searchedCode, searchedSupplier, searchedProduct);
    }

    public void sendToUpdateQuantity(int newQuantity, String productNameToUpdate) {
        databaseController.updateQuantity(newQuantity, productNameToUpdate);
    }

    public void openHandleProductFrame() {
        handleProductFrame = new HandleProductFrame(this, databaseController);
    }

    public void sendToAddDiscountPeriod(String startDate, String endDate, String productNameToUpdate, String discountToSetDate) {
        databaseController.AddDiscountUnusedPeriod(startDate, endDate, productNameToUpdate, discountToSetDate);
    }

    public ArrayList<String> getUsedDiscounts() {
        return databaseController.getUsedDiscounts();
    }

    public void openViewUsedDiscountsFrame() {
        viewUsedDiscountsFrame = new ViewUsedDiscountsFrame(this);
    }

    public void updateUsedDiscounts() {
        viewUsedDiscountsFrame.updateUsedDiscounts();
    }

    public void setAdminViewTitle() {
        applicationFrame.setAdminViewTitle();
    }

    public void setUserViewTitle() {
        applicationFrame.setUserViewTitle();
    }

    public ArrayList<String> getAllProductsUser() {
        return databaseController.getProductsForUsers();
    }

    public boolean checkQuantity(int nbrOfItems, int productID) {
        return databaseController.checkQuantity(nbrOfItems, productID);
    }

   public ArrayList<String> getOrderedProduct(int productID, int nbrOfItems) {
        return databaseController.getOrderedProduct(productID, nbrOfItems);
    }

    public void openShoppingCart() {
        shoppingCartFrame = new ShoppingCartFrame(this);
    }

    public ArrayList<String> getProductsOrdered(int productID, int nbrOfItems) {
        return databaseController.getOrderedProduct(productID, nbrOfItems);
    }

    public void openHandleOrdersFrame() {
        handleOrdersFrame = new HandleOrdersFrame(this, databaseController);
    }

    public ArrayList<String> getOrdersList() {
        return databaseController.getOrdersList();
    }

    public ArrayList<String> getProductsForCustomers() {
        return databaseController.getProductsForCustomers();
    }
}
