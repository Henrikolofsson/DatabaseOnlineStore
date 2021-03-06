package Controller;

import Database.*;
import Entities.*;
import Enums.Countries;
import GUI.AdminPanels.AddDiscountFrame.AddDiscountFrame;
import GUI.AdminPanels.AddProductFrame.AddProductFrame;
import GUI.AdminPanels.AddSupplierFrame.AddSupplierFrame;
import GUI.AdminPanels.DeleteProductFrame.DeleteProductFrame;
import GUI.AdminPanels.DisplayProductSaleFrame.DisplayProductSaleFrame;
import GUI.AdminPanels.HandleDiscountFrame.HandleDiscountFrame;
import GUI.AdminPanels.HandleOrdersFrame.HandleOrdersFrame;
import GUI.AdminPanels.HandleProductFrame.HandleProductFrame;
import GUI.AdminPanels.ViewUsedDiscountsFrame.ViewUsedDiscountsFrame;
import GUI.ApplicationFrame;
import GUI.Usercreation.CreateAccountFrame;
import Interfaces.PanelListener;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Vector;

/*
    @Author Henrik Olofsson
    @Author Alexandros Karakitsos
    @Date 13-01-2021

    The main responsible controller for all logic in the program. It opens new windows, fetches information from the database through the database controller,
    and dispatch information to given panels.
 */

public class MainController implements PanelListener {
    private DatabaseController databaseController;
    private ApplicationFrame applicationFrame;

    private CreateAccountFrame createAccountFrame;

    private AddSupplierFrame addSupplierFrame;
    private AddDiscountFrame addDiscountFrame;
    private AddProductFrame addProductFrame;

    private DeleteProductFrame deleteProductFrame;

    private HandleProductFrame handleProductFrame;
    private HandleOrdersFrame handleOrdersFrame;
    private ViewUsedDiscountsFrame viewUsedDiscountsFrame;
    private DisplayProductSaleFrame displayProductSaleFrame;
    private HandleDiscountFrame handleDiscountFrame;


    private String userName;
    private int userRole;
    private int userId;

    public MainController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        applicationFrame = new ApplicationFrame(this);
    }

    /*
        Makes a request to the database to create a new admin user.
     */

    public boolean createAdminUser(User user, String adminPassword) {
        if(databaseController.checkAdminPassword(adminPassword)) {
            if(databaseController.createUser(user, true)) {
                createAccountFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "You couldn't create the admin user");
            }
            return true;
        }
        return false;
    }

    /*
      Makes a request to the database to create a new regular user.
   */

    public boolean createNormalUser(User user) {
        if(databaseController.createUser(user, false)) {
            createAccountFrame.dispose();
            return true;
        }
        return false;
    }


    public String getFirstName(String usernameLogin, String passwordLogin) {
        return databaseController.getFirstName(usernameLogin, passwordLogin);
    }

    public Countries[] getCountries() {
        return Countries.values();
    }

    /*
        An interface function that listens on calls from all the panels.
     */

    public void btnPressed(String btn) {
        switch(btn) {
            /*
                Opening windows
             */

            case "OpenNewAccountWindow": openCreateAccountWindow();
                break;

            case "LogIn": if(validateUserLogin()) { setUpView(); }
                break;

            case "OpenAddSupplierWindow": openAddSupplierWindow();
                break;

            case "OpenAddDiscountWindow": openAddDiscountWindow();
                break;

            case "OpenAddProductWindow": openAddProductWindow();
                break;

            case "OpenDeleteProductWindow": openDeleteProductWindow();
                break;

            case "OpenHandleProductWindow": openHandleProductWindow();
                break;

            case "OpenHandleOrdersWindow": openHandleOrdersWindow();
                break;

            case "OpenViewUsedDiscountsWindow": openViewUsedDiscountsFrame();
                break;

            case "OpenHandleDiscountsWindow": openHandleDiscountsWindow();
                break;

            case "OpenGetDisplayProductSaleWindow": openDisplayProductSaleFrame();
                break;

            /*
                Adding items into database
             */

            case "AddSupplier": addSupplierToDb();
                break;

            case "AddDiscount": addDiscountToDb();
                break;

            case "AddProduct": addProductToDb();
                break;

            case "DeleteProduct": deleteProductFromDb();
                break;

            case "HandleProductQuantity": updateProductQuantity();
                break;

            case "AddQuantity": updateProductQuantity();
                break;

            case "GetAllProducts": getAllProducts();
                break;

            case "SearchForProducts":
                break;

            case "GetAllDiscounts":
                break;

            case "ViewOrders":
                break;

            case "SearchForProductsById":
                break;

            case "SearchForProductsBySupplier":
                break;


        }
    }

    /*
        Validates a user login, and if the credentials is valid, the users name and users role is fetched.
     */

    private boolean validateUserLogin() {
        Map<String, Object> map =
                databaseController.isValidLogin(applicationFrame.getUserName(), String.valueOf(applicationFrame.getPassword()));

        if(map != null) {
            this.userName = (String)map.get("user_name");
            this.userRole = (int)map.get("role");
            this.userId = (int)map.get("id");

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "The name or password is not correct");
            return false;
        }
    }

    /*
        Sets up the view, depending on the users role.
     */

    private void setUpView() {
        if(userRole == 1) {
            applicationFrame.updateAdminView();
            applicationFrame.updateAdminFirstName(userName);
            applicationFrame.setAdminViewTitle();
        } else if(userRole == 2) {
            applicationFrame.updateUserView();
            applicationFrame.updateUserFirstName(userName);
        }
    }

    /*
        Functions for opening new windows.
     */

    private void openCreateAccountWindow() {
        createAccountFrame = new CreateAccountFrame(this, databaseController);
    }

    private void openAddDiscountWindow() {
        addDiscountFrame = new AddDiscountFrame(this);
    }

    private void openAddProductWindow() {
        addProductFrame = new AddProductFrame(this);
    }

    private void openAddSupplierWindow() {
        addSupplierFrame = new AddSupplierFrame(this);
    }

    private void openDeleteProductWindow() {
        deleteProductFrame = new DeleteProductFrame(this);
    }

    private void openHandleProductWindow() {
        handleProductFrame = new HandleProductFrame(this);
    }

    private void openHandleOrdersWindow() {
        handleOrdersFrame = new HandleOrdersFrame(this);
    }

    public void openViewUsedDiscountsFrame() {
        viewUsedDiscountsFrame = new ViewUsedDiscountsFrame(this);
    }

    private void openDisplayProductSaleFrame() {
        displayProductSaleFrame = new DisplayProductSaleFrame(this);
    }

    private void openHandleDiscountsWindow() {
        handleDiscountFrame = new HandleDiscountFrame(this);
    }

    /*
        Functions for adding items to the database
     */

    private void addSupplierToDb() {
        DatabaseController.addSupplier(addSupplierFrame.getSupplier());
    }

    private void addDiscountToDb() {
        DatabaseController.addDiscount(addDiscountFrame.getDiscount());
    }

    private void addProductToDb() {
        Product product = addProductFrame.getProduct();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateToday;
        try {
            java.util.Date date = format.parse(java.time.LocalDate.now().toString());
            dateToday = new java.sql.Date(date.getTime());

            product.setProductStatus("Aktiv");
            int productId = DatabaseController.addProduct(product);
            if(productId >= 0) {

                int orderId = DatabaseController.addOrder(new Order(userId, dateToday, "INKO"));
                if(orderId >= 0) {
                    DatabaseController.addOrderRow(new OrderRow(orderId, productId, product.getProductQuantity()));
                }
                DatabaseController.addStock(new Stock(productId, dateToday, orderId, product.getProductQuantity(), "INLEV"));
            }

        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    /*
        Functions for deleting items from the database
     */

    private void deleteProductFromDb() {
        DatabaseController.deleteProduct(deleteProductFrame.getProduct());
    }

    /*
        Functions that updates items in the database
     */

    private void updateProductQuantity() {
        Map<String, Integer> map = handleProductFrame.getIdAndQuantity();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateToday;

        try {
            java.util.Date date = format.parse(java.time.LocalDate.now().toString());
            dateToday = new java.sql.Date(date.getTime());

            int productId = map.get("product_id");
            int productQuantity = map.get("quantity");

            int orderId = DatabaseController.addOrder(new Order(userId, dateToday, "INKO"));
            if(orderId >= 0) {
                DatabaseController.addOrderRow(new OrderRow(orderId, productId, productQuantity));
            }
            DatabaseController.addStock(new Stock(productId, dateToday, orderId, productQuantity, "INLEV"));
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    /*
        Functions that returns lists of data to the GUI for ComboBoxes, JList etc
     */

    public Vector<ComboBoxItem> getSuppliersForAddProductPanel(){
        return QueryController.getSuppliers();
    }

    public Vector<ComboBoxItem> getActiveProducts() {
        return QueryController.getActiveProducts();
    }

    public Vector<ListItem> getAllProducts() {
        return QueryController.getAllProducts();
    }


    public Vector<ListItem> getProductsByName(String text) {
        return QueryController.getProductsByName(text);
    }

    public Vector<ListItem> getAllDiscountedProducts() {
        return QueryController.getAllDiscountedProducts();
    }

    public Vector<ListItem> getProductsById(String id) {
        return QueryController.getProductsById(Integer.parseInt(id));
    }

    public Vector<ListItem> getProductsBySupplier(String text) {
        return QueryController.getProductsBySupplier(text);
    }

    public boolean isProductAvailable(int productId, int quantity) {
        return QueryController.isProductAvailable(productId, quantity);
    }

    public int createCustomerOrder() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateToday;

        try {
            java.util.Date date = format.parse(java.time.LocalDate.now().toString());
            dateToday = new java.sql.Date(date.getTime());
            return InsertController.addOrder(new Order(userId, dateToday, "KO"));
        } catch(ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void reserveProduct(int orderId, int productId, int quantity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateToday;

        try {
            java.util.Date date = format.parse(java.time.LocalDate.now().toString());
            dateToday = new java.sql.Date(date.getTime());
            InsertController.reserveProduct(orderId,productId, quantity, dateToday);
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean stockEntryExists(int productId, int orderId) {
        return QueryController.stockEntryExists(productId, orderId);
    }

    public void updateStock(int productId, int orderId, int quantity) {
        UpdateController.updateStock(productId, orderId, quantity);
    }

    public void addStock(int productId, int orderId, int quantity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateToday;

        try {
            java.util.Date date = format.parse(java.time.LocalDate.now().toString());
            dateToday = new java.sql.Date(date.getTime());
            InsertController.reserveProduct(orderId,productId, quantity, dateToday);
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    public void placeOrder(int orderId) {
        UpdateController.placeOrder(orderId);
    }

    public void addOrderRow(int orderId, OrderItem item) {
        InsertController.addOrderRow(new OrderRow(orderId, item.getProductId(), item.getQuantity()));
    }

    public Vector<ListItem> getAllOrders() {
        return QueryController.getAllOrders(userId);
    }

    public Vector<ListItem> getAllUsersOrders() {
        return QueryController.getAllUsersOrders();
    }

    public void removeOrder(int itemValue) {
        DeleteController.removeOrderAndStockEntry(itemValue);
    }

    public void confirmOrder(int itemValue) {
        UpdateController.confirmOrder(itemValue);
    }


    public Vector<ListItem> getSales(String month) {
        return switch (month) {
            case "January" -> QueryController.getProductSales("2021-01-01", "2021-01-31");
            case "February" -> QueryController.getProductSales("2021-02-01", "2021-02-28");
            case "March" -> QueryController.getProductSales("2021-03-01", "2021-03-31");
            case "April" -> QueryController.getProductSales("2021-04-01", "2021-04-30");
            case "May" -> QueryController.getProductSales("2021-05-01", "2021-05-31");
            case "June" -> QueryController.getProductSales("2021-06-01", "2021-06-30");
            case "July" -> QueryController.getProductSales("2021-07-01", "2021-07-31");
            case "August" -> QueryController.getProductSales("2021-08-01", "2021-08-31");
            case "September" -> QueryController.getProductSales("2021-09-01", "2021-09-30");
            case "October" -> QueryController.getProductSales("2021-10-01", "2021-10-31");
            case "November" -> QueryController.getProductSales("2021-11-01", "2021-11-30");
            case "December" -> QueryController.getProductSales("2021-12-01", "2021-12-31");
            default -> null;
        };
    }

    public Vector<ListItem> getDiscountHistory() {
        return QueryController.getDiscountHistory();
    }

    public Vector<ComboBoxItem> getAllDiscounts() {
        return QueryController.getAllDiscounts();
    }

    public void addProductDiscount(int productId, int discountId) {
        InsertController.addProductDiscount(productId, discountId);
    }
}
