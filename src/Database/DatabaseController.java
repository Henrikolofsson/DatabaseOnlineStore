package Database;

import Entities.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
    DAO - Database access object
 */
public class DatabaseController {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/OnlineStore";
    private static String user = "postgres";
    private static String password = "123Henrik123";
    private static Connection connection;







    /*
        INSERTS
     */

    public boolean createUser(User user, boolean isAdmin) {
        return InsertController.createUser(this, user, isAdmin);
    }

    //TODO: Actually send the objects to db
    public static void addSupplier(Supplier supplier) {
        InsertController.addSupplier(supplier);
    }

    public static void addDiscount(Discount discount) {
        InsertController.addDiscount(discount);
    }

    public static int addProduct(Product product) {
        return InsertController.addProduct(product);
    }

    public static int addOrder(Order order) {
        return InsertController.addOrder(order);
    }

    public static void addOrderRow(OrderRow orderRow) {
        InsertController.addOrderRow(orderRow);
    }

    public static void addStock(Stock stock) {
        InsertController.addStock(stock);
    }

    /*
        QUERIES
     */

    public boolean checkAdminPassword(String adminPassword) {
        return QueryController.checkAdminPassword(adminPassword);
    }

    public boolean checkIfAlreadyExists(String userName) {
        return QueryController.checkIfAlreadyExists(userName);
    }

    public int getUserId(String userName) {
        return QueryController.getUserId(userName);
    }

    public int getUserInformationId(String email) {
        return QueryController.getUserInformationId(email);
    }

    public boolean isUserNormal(String userNameLogin, String passwordLogin) {
        return QueryController.isUserNormal(userNameLogin, passwordLogin);
    }

    public boolean isUserAdmin(String userNameLogin, String passwordLogin) {
        return QueryController.isUserAdmin(userNameLogin, passwordLogin);
    }

    public String getFirstName(String usernameLogin, String passwordLogin) {
        return "Hoe";
    }

    public Map<String, Object> isValidLogin(String userName, String password) {
        return QueryController.isValidLogin(userName, password);
    }

    /*
        DELETES
     */

    public static void deleteProduct(int product) {
        DeleteController.deleteProduct(product);
    }

    /*
        UPDATES
     */







}
