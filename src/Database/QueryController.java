package Database;

import Entities.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class QueryController {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/OnlineStore";
    private static String user = "postgres";
    private static String password = "123Henrik123";
    private static Connection connection;

    /*
    Opens up a connection to the database.
    */
    public static Connection connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, user, password);
            //    System.out.println("Connection to database successful.");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /*
        Shuts down the connection to the database.
     */
    public static void disconnect() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch(SQLException e) {
            System.out.println("Failed to disconnect from database.");
        }
    }

    /*
        Checks if the admin password is correct in the database.
     */

    protected static boolean checkAdminPassword(String adminPassword) {
        connect();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin_password WHERE EXISTS (SELECT admin_password);");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).equals(adminPassword)) {
                    return true;
                }
            }
            statement.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   public static boolean checkIfAlreadyExists(String username) {
        connect();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT user_name FROM users WHERE EXISTS (SELECT user_name);");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals(username)) {
                    JOptionPane.showMessageDialog(null, "This username already exists.\nPlease choose a different username.");

                    statement.close();
                    rs.close();
                    disconnect();
                    return true;
                }
            }
            statement.close();
            rs.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getUserId(String userName) {
        connect();
        int id = -1;
        
        try {
            String query_user_id = "SELECT * FROM users WHERE users.user_name = '" + userName + "';";
            PreparedStatement statement = connection.prepareStatement(query_user_id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                id = Integer.parseInt(rs.getString(1));
                rs.close();
            }
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getUserInformationId(String email) {
        connect();
        int id = -1;

        try {
            String query_user_information_id = "SELECT * from user_information WHERE email = '" + email + "';";
            PreparedStatement statement = connection.prepareStatement(query_user_information_id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                id = Integer.parseInt(rs.getString(1));
                rs.close();
            }
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    protected static boolean isUserNormal(String usernameLogin, String passwordLogin){
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT user_name, user_password FROM users");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals(usernameLogin.toLowerCase()) && rs.getString(2).equals(passwordLogin)) {
                    stmt.close();
                    rs.close();
                    disconnect();
                    return true;
                }
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean isUserAdmin(String usernameLogin, String passwordLogin) {
        connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT admin_name, admin_password FROM admins");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals(usernameLogin.toLowerCase()) && rs.getString(2).equals(passwordLogin)) {
                    stmt.close();
                    rs.close();
                    disconnect();
                    return true;
                }
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static Map<String, Object> isValidLogin(String userName, String password) {
        connect();

        try {
            Map<String, Object> map = null;
            String str = "SELECT user_name, role, id FROM users WHERE user_name = '" + userName + "' AND password = '" + password + "';";
            System.out.println(str);
            PreparedStatement stmt = connection.prepareStatement(str);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                map = new HashMap<>(3);
                map.put("user_name", rs.getString(1));
                map.put("role", rs.getInt(2));
                map.put("id", rs.getInt(3));
            }
            rs.close();
            stmt.close();
            disconnect();
            return map;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<ComboBoxItem> getSuppliers() {
        connect();

        Vector<ComboBoxItem> suppliers = new Vector<ComboBoxItem>();
        try {
            String query =
                    "SELECT name, id FROM supplier ORDER BY name ASC";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getInt(2));
                suppliers.add(new ComboBoxItem(rs.getString(1), rs.getInt(2)));
            }

            rs.close();
            return suppliers;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();

        return null;
    }

    public static Vector<ComboBoxItem> getActiveProducts() {
        connect();

        Vector<ComboBoxItem> activeProducts = new Vector<ComboBoxItem>();

        try {
            String query = "SELECT id, name FROM products WHERE status LIKE 'Aktiv';";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                activeProducts.add(new ComboBoxItem(rs.getString(2),rs.getInt(1)));
            }
            rs.close();
            disconnect();

            return activeProducts;


        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<ComboBoxItem> getAllProducts() {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100)) " +
                            "FROM products prod " +
                            "LEFT JOIN assigned_discounts adc ON prod.id = adc.product_id " +
                            "LEFT JOIN discount dis ON adc.discount_id = dis.id " +
                            "LEFT JOIN supplier sup ON prod.supplier_id = sup.id " +
                            "WHERE prod.status = 'Aktiv';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

           Vector<ComboBoxItem> allProducts = new Vector<>();
           ProductInformation productInformation;
           while(rs.next()) {
               if(rs.getInt(4) > 0) {
                   productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                           rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                   allProducts.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
               } else {
                   productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                           rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                   allProducts.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
               }
           }

            rs.close();
            disconnect();
            return allProducts;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<ComboBoxItem> getProductsByName(String text) {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100)) " +
                    "FROM products prod " +
                    "LEFT JOIN assigned_discounts adc ON prod.id = adc.product_id " +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id " +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id " +
                    "WHERE prod.status = 'Aktiv'" +
                    "AND prod.name = '" + text + "';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Vector<ComboBoxItem> allProducts = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    allProducts.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    allProducts.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
                }
            }

            rs.close();
            disconnect();
            return allProducts;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<ComboBoxItem> getAllDiscountedProducts() {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))" +
                    "FROM products prod " +
                    "INNER JOIN assigned_discounts adc ON prod.id = adc.product_id " +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id " +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id " +
                    "WHERE prod.status = 'Aktiv' " +
                    "AND dis.end_date > CURRENT_DATE;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Vector<ComboBoxItem> allDiscountedProducts = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    allDiscountedProducts.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    allDiscountedProducts.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
                }

            }

            rs.close();
            disconnect();
            return allDiscountedProducts;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<ComboBoxItem> getProductsById(int productId) {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))" +
                    "FROM products prod " +
                    "INNER JOIN assigned_discounts adc ON prod.id = adc.product_id " +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id " +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id " +
                    "WHERE prod.status = 'Aktiv' " +
                    "AND prod.id = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            Vector<ComboBoxItem> allProductsById = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    allProductsById.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    allProductsById.add(new ComboBoxItem(productInformation.toString(), productInformation.getProductId()));
                }

            }

            rs.close();
            disconnect();
            return allProductsById;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*

     *//*
        USER RELATED
        ---------------------------------------------------------------------------------------------------------------------------------------------------------
     *//*


    public void retrieveFirstName(String userNameLogin) {
        connect();
        try {

            *//*String query =
                    "SELECT user_fname, admin_fname \n" +
                    "FROM users  \n" +
                    "INNER JOIN admins ON user_id = admin_id\n" +
                    "WHERE user_name LIKE ?";*//*

            String query =
                    "SELECT user_fname FROM users WHERE user_name LIKE ?;";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userNameLogin);

            ResultSet rs = stmt.executeQuery();

            String firstName = "";

            while(rs.next()) {
                firstName = rs.getString(1);
            }
            LoggedIn loggedIn = new LoggedIn();
            loggedIn.updateLabelLoggedIn(firstName);
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName(String usernameLogin, String passwordLogin) {
        String firstname = "";

        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT user_name, user_password, user_fname, admin_name, admin_password, admin_fname FROM users, admins");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals(usernameLogin.toLowerCase()) && rs.getString(2).equals(passwordLogin)) {
                    firstname = rs.getString(3);

                } else if (rs.getString(4).toLowerCase().equals(usernameLogin.toLowerCase()) && rs.getString(5).equals(passwordLogin)){
                    firstname = rs.getString(6);
                }
            }
            stmt.close();
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return firstname;
    }



    *//*
        PRODUCT RELATED
        ---------------------------------------------------------------------------------------------------------------------------------------------------------
     *//*
    public boolean checkQuantity(int nbrOfItems, int productID) {
        connect();

        int quantity = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT product_quantity FROM products WHERE (product_id) = ?");
            stmt.setBigDecimal(1, new BigDecimal(productID));
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                quantity = rs.getInt(1);
            }

            stmt.close();
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if(quantity < nbrOfItems) {
            JOptionPane.showMessageDialog(null, "You added more products than there are available");
            return false;
        } else {
            int newQuantity = (quantity - nbrOfItems);
            addProductToShoppingCart(newQuantity, productID);
            return true;
        }
    }

    public ArrayList<String> getAllProducts() {
        ArrayList<String> allProducts = new ArrayList<>();
        connect();
        try {
            String query =
                    "SELECT product_id, product_name, product_quantity, product_price, product_supplier FROM products ORDER BY product_id ASC";

            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                String productToAdd =
                        "Code: "+ rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                                " | Base Price: " + rs.getString(4) + ":- | Supplier: " + rs.getString(5);
                allProducts.add(i, productToAdd);
                i++;
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return allProducts;
    }

    public ArrayList<String> getProductsForCustomers(){
        ArrayList<String> products = new ArrayList<>();
        connect();
        String query = "";
        int j = 0;

        try{
            query =
                    """
                            SELECT pt.product_id, pt.product_name, pt.product_quantity, pt.product_price, pt.product_supplier, dt.discount_reason, dt.discount_percentage\s
                            FROM products pt
                            INNER JOIN used_discounts udt ON pt.product_id = udt.product_id
                            LEFT JOIN discounts dt ON dt.discount_code = udt.used_discount_id
                            AND udt.used_discount_id = dt.discount_code
                            WHERE udt.used_discount_enddate > CURRENT_DATE
                            AND pt.product_quantity > 0
                            ORDER BY pt.product_id ASC""";

            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                String discountReason = rs.getString(6);
                double calcDiscount = (rs.getDouble(4) * rs.getDouble(7));
                double finalPrice = Math.round((rs.getDouble(4) - calcDiscount));

                String productToAdd =
                        "Code: "+ rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                                " | Price: " + finalPrice + ":- | Discount reason: " + discountReason + " | Discount Percentage: " + Math.round(rs.getDouble(7) * 100) +
                                "% | Supplier: " + rs.getString(5);
                products.add(i, productToAdd);
                i++;
                j++;
            }

            stmt.close();
            rs.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connect();

        try {
            query =
                    """
                            SELECT pt.product_id, pt.product_name, pt.product_quantity, pt.product_price, pt.product_supplier, dt.discount_reason, dt.discount_percentage
                            FROM products pt
                            LEFT JOIN discounts dt ON dt.discount_code = pt.discount_code
                            WHERE product_quantity > 0
                            AND discount_reason IS NULL
                            ORDER BY pt.product_id ASC""";

            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String discountReason = "";
                double finalPrice = 0;
                String productToAdd = "";

                discountReason = "None";
                finalPrice = rs.getDouble(4);
                productToAdd =
                        "Code: " + rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                                " | Price: " + finalPrice + ":- | Discount reason: " + discountReason +
                                " | Supplier: " + rs.getString(5);
                products.add(j, productToAdd);
                j++;
            }

            stmt.close();
            rs.close();
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(products);
        return products;
    }

    public ArrayList<String> getProducts() {
        ArrayList<String> products = new ArrayList<>();
        connect();
        try {
            String query =
                    "SELECT product_name FROM products ORDER BY product_name ASC";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                products.add(i, rs.getString(1));
                i++;
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return products;
    }

    public ArrayList<String> getProductsForUsers() {
        ArrayList<String> products = new ArrayList<>();
        connect();
        try {
            String query =
                    """
                            SELECT product_name FROM products 
                            WHERE product_quantity > 0
                            ORDER BY product_name ASC""";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                products.add(i, rs.getString(1));
                i++;
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return products;
    }

    public ArrayList<String> getOrderedProduct(int productID, int nbrOfItems) {
        ArrayList<String> productsAdded = new ArrayList<>();

        return productsAdded;
    }

    *//*
      DISCOUNT RELATED
      ---------------------------------------------------------------------------------------------------------------------------------------------------------
   *//*
    public ArrayList<String> getUsedDiscounts() {
        ArrayList<String> usedDiscounts = new ArrayList<>();

        connect();
        try {
            String query =
                    """
                            SELECT pt.product_name, udt.used_discount_startdate, udt.used_discount_enddate, dt.discount_percentage, dt.discount_reason, pt.product_price
                            FROM used_discounts udt
                            INNER JOIN products pt on udt.product_id = pt.product_id
                            LEFT JOIN discounts dt on udt.used_discount_id = dt.discount_code
                            WHERE udt.product_id = pt.product_id
                            ORDER BY dt.discount_reason ASC""";

            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                double percentage = (Math.round(rs.getDouble(4) * 100));
                double calcDiscount = (rs.getDouble(6) * rs.getDouble(4));
                double finalPrice = Math.round((rs.getDouble(6) - calcDiscount));

                String strUsedDiscounts =
                        rs.getString(1) + " | " + rs.getString(2) + "-" +
                                rs.getString(3) + " | " + percentage + "% | " +
                                rs.getString(5) + " | " + finalPrice + ":-";

                usedDiscounts.add(i, strUsedDiscounts);
                i++;
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return usedDiscounts;
    }

    public ArrayList<String> getUserSearchedProducts(String searchedCode, String searchedString, String searchedProduct) {
        ArrayList<String> searchedProductsUser = new ArrayList<>();
        connect();
        try {
            String query = "";
            if(searchedString.toLowerCase().equals("discounts")){
                query =
                        """
                                SELECT pt.product_id, pt.product_name, pt.product_quantity, pt.product_price, pt.product_supplier, dt.discount_reason, dt.discount_percentage 
                                FROM products pt
                                INNER JOIN used_discounts udt ON pt.product_id = udt.product_id
                                LEFT JOIN discounts dt ON dt.discount_code = udt.used_discount_id
                                AND udt.used_discount_id = dt.discount_code
                                WHERE product_quantity > 0
                                AND pt.discount_code IS NOT NULL
                                AND udt.used_discount_enddate > CURRENT_DATE
                                ORDER BY pt.product_id ASC""";

                PreparedStatement stmt = connection.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();

                int i = 0;
                while(rs.next()) {
                    String discountReason = rs.getString(6);
                    double calcDiscount = (rs.getDouble(4) * rs.getDouble(7));
                    double finalPrice = Math.round((rs.getDouble(4) - calcDiscount));


                    String productToAdd =
                            "Code: "+ rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                                    " | Price: " + finalPrice + ":- | Discount reason: " + discountReason + " | Discount Percentage: " + Math.round(rs.getDouble(7) * 100) +
                                    "% | Supplier: " + rs.getString(5);
                    searchedProductsUser.add(i, productToAdd);
                    i++;
                }
                stmt.close();
                rs.close();
            } else {
                query =
                        """
                                SELECT pt.product_id, pt.product_name, pt.product_quantity, pt.product_price, pt.product_supplier, dt.discount_reason, dt.discount_percentage
                                FROM products pt
                                LEFT JOIN discounts dt ON dt.discount_code = pt.discount_code
                                WHERE product_quantity > 0
                                AND product_id = ?
                                OR LOWER(product_name) LIKE ?
                                OR LOWER(product_name) LIKE ?
                                ORDER BY pt.product_id ASC""";

                PreparedStatement stmt = connection.prepareStatement(query);

                if(searchedCode == null){
                    stmt.setNull(1, Types.INTEGER);
                    stmt.setString(2, searchedString.toLowerCase());
                    stmt.setString(3,  searchedString.toLowerCase());
                } else {
                    int codeInt = Integer.parseInt(searchedCode);
                    stmt.setBigDecimal(1, new BigDecimal(codeInt));
                    stmt.setNull(2, Types.VARCHAR);
                    stmt.setNull(3, Types.VARCHAR);
                }

                ResultSet rs = stmt.executeQuery();

                int i = 0;

                while(rs.next()) {
                    String discountReason = "";
                    double calcDiscount = 1;
                    double finalPrice = 0;
                    String productToAdd = "";

                    if(rs.getString(6) == null){
                        discountReason = "None";
                        finalPrice = rs.getDouble(4);
                        productToAdd =
                                "Code: "+ rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                                        " | Price: " + finalPrice + ":- | Discount reason: " + discountReason +
                                        " | Supplier: " + rs.getString(5);
                        searchedProductsUser.add(i, productToAdd);
                        continue;

                    } else {
                        discountReason = rs.getString(6);
                        calcDiscount = (rs.getDouble(4) * rs.getDouble(7));
                        finalPrice = Math.round((rs.getDouble(4) - calcDiscount));
                    }
                    productToAdd =
                            "Code: "+ rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                                    " | Price: " + finalPrice + ":- | Discount reason: " + discountReason + " | Discount Percentage: " + Math.round(rs.getDouble(7) * 100) +
                                    "% | Supplier: " + rs.getString(5);
                    searchedProductsUser.add(i, productToAdd);
                    i++;
                }
                stmt.close();
                rs.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return searchedProductsUser;
    }

    public ArrayList<String> getDiscounts() {
        connect();
        ArrayList<String> discounts = new ArrayList<String>();
        //String[] suppliers = new String[10];
        try {
            String query =
                    "SELECT discount_reason FROM discounts";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                discounts.add(i, rs.getString(1));
                i++;
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    *//*
     SUPPLIER RELATED
     ---------------------------------------------------------------------------------------------------------------------------------------------------------
  *//*


     *//*
      ORDER RELATED
      ---------------------------------------------------------------------------------------------------------------------------------------------------------
   *//*
    public ArrayList<String> getOrdersList() {
        ArrayList<String> orders = new ArrayList<>();
        connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                    
                    """);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {

            }
            statement.close();
            rs.close();
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

*/


}
