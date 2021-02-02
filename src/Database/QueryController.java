package Database;

import Entities.*;
import org.postgresql.util.PSQLException;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static Vector<ComboBoxItem> getNonOrderedProducts() {
        connect();

        Vector<ComboBoxItem> activeProducts = new Vector<ComboBoxItem>();

        try {
            String query = "SELECT prod.id, prod.name, st.status\n" +
                    "FROM products prod\n" +
                    "INNER JOIN stocks st ON prod.id = st.product_id\n" +
                    "WHERE prod.status LIKE 'Aktiv'\n" +
                    "AND st.status LIKE 'RES'\n" +
                    "OR\n" +
                    "WHERE prod.status LIKE 'Aktiv'\n" +
                    "AND st.status LIKE 'SHIPPED';";
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

    public static Vector<ListItem> getAllProducts() {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))\n" +
                    "FROM products prod \n" +
                    "LEFT JOIN assigned_discounts adc ON prod.id = adc.product_id \n" +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id\n" +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id\n" +
                    "WHERE prod.status = 'Aktiv'\n" +
                    "AND (dis.end_date > CURRENT_DATE AND dis.start_date < CURRENT_DATE)\n" +
                    "OR prod.status = 'Aktiv' AND dis.end_date IS null;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

           Vector<ListItem> allProducts = new Vector<>();
           ProductInformation productInformation;
           while(rs.next()) {
               if(rs.getInt(4) > 0) {
                   productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                           rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                   ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getDiscountedPrice());
                   allProducts.add(listItem);
                   System.out.println(listItem);
                   //System.out.println(productInformation.toString() + ":" + productInformation.getProductId() + ":" + productInformation.getProductPrice());
               } else {
                   productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                           rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                   ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice());
                   allProducts.add(listItem);
                   System.out.println(listItem);
                   //System.out.println(productInformation.toString() + ":" + productInformation.getProductId() + ":" + productInformation.getProductPrice());
                   //allProducts.add(new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice()));
               }
           }



            rs.close();
            disconnect();
            return allProducts;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<ListItem> getProductsByName(String text) {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))\n" +
                    "FROM products prod \n" +
                    "LEFT JOIN assigned_discounts adc ON prod.id = adc.product_id \n" +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id\n" +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id\n" +
                    "WHERE prod.status = 'Aktiv'\n" +
                    "AND (dis.end_date > CURRENT_DATE AND dis.start_date < CURRENT_DATE) AND prod.name = '" + text + "'\n" +
                    "OR prod.status = 'Aktiv' AND dis.end_date IS null AND prod.name = '" + text + "';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Vector<ListItem> allProducts = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getDiscountedPrice());
                    allProducts.add(listItem);
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice());
                    allProducts.add(listItem);
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

    public static Vector<ListItem> getAllDiscountedProducts() {
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

            Vector<ListItem> allDiscountedProducts = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getDiscountedPrice());
                    allDiscountedProducts.add(listItem);
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice());
                    allDiscountedProducts.add(listItem);
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

    public static Vector<ListItem> getProductsById(int productId) {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))\n" +
                    "FROM products prod \n" +
                    "LEFT JOIN assigned_discounts adc ON prod.id = adc.product_id \n" +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id\n" +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id\n" +
                    "WHERE prod.status = 'Aktiv'\n" +
                    "AND (dis.end_date > CURRENT_DATE AND dis.start_date < CURRENT_DATE) AND prod.id = ?" +
                    "OR prod.status = 'Aktiv' AND dis.end_date IS null AND prod.id = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setInt(2, productId);
            ResultSet rs = statement.executeQuery();

            Vector<ListItem> allProductsById = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getDiscountedPrice());
                    allProductsById.add(listItem);
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice());
                    allProductsById.add(listItem);
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

    public static Vector<ListItem> getProductsBySupplier(String text) {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))\n" +
                    "FROM products prod \n" +
                    "LEFT JOIN assigned_discounts adc ON prod.id = adc.product_id \n" +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id\n" +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id\n" +
                    "WHERE prod.status = 'Aktiv'\n" +
                    "AND (dis.end_date > CURRENT_DATE AND dis.start_date < CURRENT_DATE) AND sup.name = ?" +
                    "OR prod.status = 'Aktiv' AND dis.end_date IS null AND sup.name = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, text);
            statement.setString(2, text);
            ResultSet rs = statement.executeQuery();

            Vector<ListItem> allProductsBySupplier = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getDiscountedPrice());
                    allProductsBySupplier.add(listItem);
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice());
                    allProductsBySupplier.add(listItem);
                }

            }

            rs.close();
            disconnect();
            return allProductsBySupplier;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isProductAvailable(int productId, int quantity) {
        connect();

        try {
            String query = "SELECT SUM(amount) AS total\n" +
                    "FROM stock\n" +
                    "WHERE product_id = ?\n" +
                    "AND status LIKE 'INLEV'\n" +
                    "OR\n" +
                    "product_id = ?\n" +
                    "AND status LIKE 'RES'\n" +
                    "OR\n" +
                    "product_id = ?\n" +
                    "AND status LIKE 'SHIPPED';";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setInt(2, productId);
            statement.setInt(3, productId);
            ResultSet rs = statement.executeQuery();
            int amount = -1;

            while(rs.next()) {
                amount = rs.getInt(1);
            }

            return (amount >= quantity);

        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stockEntryExists(int productId, int orderId) {
        connect();

        try {
            String query = "SELECT id FROM stock WHERE product_id = ? AND order_id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setInt(2, orderId);
            ResultSet rs = statement.executeQuery();


            int id = -1;
            while(rs.next()) {
                id = rs.getInt(1);
            }
             System.out.println(id);
            return (id > 0);


        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Vector<ListItem> getAllOrders(int userId) {
        connect();

        try {
            String query = "SELECT id, date, status FROM orders WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            Vector<ListItem> items = new Vector<>();

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String itemText = rs.getInt(1) + " | " + rs.getDate(2) + " | " + rs.getString(3);
                items.add(new ListItem(itemText, rs.getInt(1), -1));
            }
            rs.close();
            disconnect();
            return items;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<ListItem> getAllUsersOrders() {
        connect();

        try {
            String query = "SELECT id, date, status FROM orders WHERE status = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "PENDING");
            Vector<ListItem> items = new Vector<>();

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String itemText = rs.getInt(1) + " | " + rs.getDate(2) + " | " + rs.getString(3);
                items.add(new ListItem(itemText, rs.getInt(1), -1));
            }
            rs.close();
            disconnect();
            return items;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<ListItem> getProductSales(String startDate, String endDate) {
        connect();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateStart;
        java.sql.Date dateEnd;
        Vector<ListItem> listItems = new Vector<>();
        try {
            java.util.Date dateSt = format.parse(startDate);
            java.util.Date dateEd = format.parse(endDate);
            dateStart = new java.sql.Date(dateSt.getTime());
            dateEnd = new java.sql.Date(dateEd.getTime());

            String query = "SELECT SUM(st.amount) AS total, st.product_id, prod.name FROM stock st\n" +
                    "INNER JOIN products prod ON st.product_id = prod.id\n" +
                    "WHERE st.status LIKE 'APPROVED' \n" +
                    "AND date BETWEEN ? and ?\n" +
                    "GROUP BY product_id, prod.name\n" +
                    "ORDER BY product_id DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, dateStart);
            statement.setDate(2, dateEnd);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                listItems.add(new ListItem(rs.getString(3) + " | Amount: " + String.valueOf(rs.getInt(1)).substring(1), rs.getInt(2), 0));
            }
            rs.close();
            disconnect();
            return listItems;
        } catch(ParseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Vector<ListItem> getDiscountHistory() {
        connect();

        try {
            String query = "SELECT prod.id, prod.name, prod.price, adc.discount_id, dis.reason, sup.name, dis.percentage, (prod.price * ((100 - dis.percentage) / 100))" +
                    "FROM products prod " +
                    "INNER JOIN assigned_discounts adc ON prod.id = adc.product_id " +
                    "LEFT JOIN discount dis ON adc.discount_id = dis.id " +
                    "LEFT JOIN supplier sup ON prod.supplier_id = sup.id " +
                    "WHERE prod.status = 'Aktiv';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Vector<ListItem> allDiscountedProducts = new Vector<>();
            ProductInformation productInformation;
            while(rs.next()) {
                if(rs.getInt(4) > 0) {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getDiscountedPrice());
                    allDiscountedProducts.add(listItem);
                } else {
                    productInformation = new ProductInformation(rs.getInt(1), rs.getString(2), rs.getDouble(3), -1,
                            rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
                    ListItem listItem = new ListItem(productInformation.toString(), productInformation.getProductId(), productInformation.getProductPrice());
                    allDiscountedProducts.add(listItem);
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

    public static Vector<ComboBoxItem> getAllDiscounts() {
        connect();

        try {
            String query = "SELECT id, reason FROM discount;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Vector<ComboBoxItem> items = new Vector<>();
            while(rs.next()) {
                items.add(new ComboBoxItem(rs.getString(2), rs.getInt(1)));
            }
            return items;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
