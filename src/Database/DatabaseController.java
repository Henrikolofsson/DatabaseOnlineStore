package Database;

import Entities.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/onlinestore";
    private static String user = "postgres";
    private static String password = "Alexandros1.";
    private static Connection connection;

    public DatabaseController() {

    }

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
      Checks if a table for Users exist, if it does not - it will create a table for Users.
      After that it inserts the user into the table.
    */
    //TODO: ändra efter detta projektet
    /* public static boolean addWorker(String workerPersonNumber, String workerName, String workerAddress) {
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS workers (" +
                    "worker_id VARCHAR(12) PRIMARY KEY NOT NULL," +
                    "worker_name TEXT NOT NULL," +
                    "worker_address TEXT NOT NULL);");
            statement.execute();
            statement.close();

            statement = connection.prepareStatement("INSERT INTO workers (worker_id, worker_name, worker_address)" +
                    " VALUES ('"+ workerPersonNumber + "','" + workerName + "','" + workerAddress + "');");
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;

        } catch(PSQLException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/

    public boolean checkAdminPassword(String password) {
       connect();

       try {
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin_password WHERE EXISTS (SELECT admin_password);");
           ResultSet rs = statement.executeQuery();

           while(rs.next()) {
               if(rs.getString(1).equals(password)) {
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

    public boolean checkIfAlreadyExists(String username) {
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
                   return false;
               }
           }
           statement.close();
           rs.close();
           disconnect();
       } catch (SQLException e) {
           e.printStackTrace();
       }

       connect();

       try {
           PreparedStatement statement = connection.prepareStatement("SELECT admin_name FROM admins WHERE EXISTS (SELECT admin_name);");
           ResultSet rs = statement.executeQuery();

           while (rs.next()) {
               if (rs.getString(1).equals(username)) {
                   JOptionPane.showMessageDialog(null, "This username already exists.\nPlease choose a different username.");

                   statement.close();
                   rs.close();
                   disconnect();
                   return false;

               }
           }
           statement.close();
           rs.close();
           disconnect();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return true;
    }

       public boolean createAdminUser(User user) {
        connect();
        try {
            String query =
                    "INSERT INTO admins(admin_name, admin_fname , admin_lname, admin_password, admin_email, admin_address, admin_city, admin_country, admin_phone, admin_id) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getPhone());

            preparedStatement.execute();
            connection.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createNormalUser(User user) {
        connect();

        try {

            String query =
                    "INSERT INTO users(user_id, user_name, user_fname , user_lname, user_password, user_email, user_address, user_city, user_country, user_phone) " +
                    "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getPhone());


            preparedStatement.execute();
            connection.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUserNormal(String usernameLogin, String passwordLogin){
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

    public boolean isUserAdmin(String usernameLogin, String passwordLogin) {
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

    public boolean addSupplier(Supplier supplier) {
        connect();

        try {
            String query =
                    "INSERT INTO suppliers(supplier_id, supplier_address, supplier_phone, supplier_name) " +
                            "VALUES(DEFAULT, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.getSupplierAddress());
            preparedStatement.setString(2, supplier.getSupplierPhone());
            preparedStatement.setString(3, supplier.getSupplierName());

            preparedStatement.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Supplier added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return false;
    }

    public boolean addProduct(Product product) {
        connect();

        try {
            int discountCode = 0;
            String query =
                    "INSERT INTO products(product_id, product_name, product_quantity, discount_code, product_price, product_supplier)\n" +
                    "VALUES(DEFAULT, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, new BigDecimal(product.getProductQuantity()));
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setBigDecimal(4, new BigDecimal(product.getProductPrice()));
            preparedStatement.setString(5, product.getProductSupplier());

            preparedStatement.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Product added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return false;
    }

    public void retrieveFirstName(String userNameLogin) {
        connect();
        try {

            /*String query =
                    "SELECT user_fname, admin_fname \n" +
                    "FROM users  \n" +
                    "INNER JOIN admins ON user_id = admin_id\n" +
                    "WHERE user_name LIKE ?";*/

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

    public ArrayList<String> getSuppliers() {
        connect();
        ArrayList<String> suppliers = new ArrayList<String>();
        //String[] suppliers = new String[10];
        try {
            String query =
                    "SELECT supplier_name FROM suppliers ORDER BY supplier_name ASC";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                suppliers.add(i, rs.getString(1));
                i++;
            }
            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
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

    public boolean addDiscount(Discount discount) {
        connect();

        try {
            String query =
                    "INSERT INTO discounts(discount_code, discount_percentage, discount_reason)" +
                    "VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBigDecimal(1, new BigDecimal(discount.getDiscountCode()));
            preparedStatement.setBigDecimal(2, BigDecimal.valueOf(discount.getDiscountPercentage()));
            preparedStatement.setString(3, discount.getDiscountReason());

            preparedStatement.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Discount added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return false;
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

    public boolean deleteProduct(String productNameToDelete) {
        connect();

        /* TODO:
        KUNNA TA BORT PRODUKT ÄVEN OM DISCOUNT ÄR APPLICERAD. DETTA GÅR I NULÄGET INTE PGA ATT
        used_discounts ANVÄNDER PRODUCT_ID SOM PRIMARY KEY. KAN ENKELT FIXAS MED ATT HÄMTA ID FRÅN products OCH
        APPLICERA DEN PÅ used_discounts. FRÅGAN ÄR: SKA 'BORTTAGNA' PRODUKTER SYNAS NÄR MAN KOLLAR GAMLA PRODUKTERS
        DISCOUNT HISTORY? MAN HADE KUNANT SPARA PRODUCT_ID SOM EN SEPARAT KOLUMN MEN DÅ BLIR DET ÖVERFLÖDIG DATA
         */

        try {
            String query =
                    """
                        DELETE FROM products WHERE LOWER(product_name) LIKE ?""";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productNameToDelete.toLowerCase());

            preparedStatement.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Product removed!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return false;
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
                                 SELECT pt.product_id, pt.product_name, pt.product_quantity, pt.product_price, pt.product_supplier, dt.discount_reason, dt.discount_percentage\s
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

    public boolean updateQuantity(int newQuantity, String productNameToUpdate) {
        connect();

        try {
            String query =
                    "UPDATE products\n" +
                    "SET product_quantity = ?" +
                    "WHERE product_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBigDecimal(1, new BigDecimal(newQuantity));
            preparedStatement.setString(2, productNameToUpdate);

            preparedStatement.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Quantity updated!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return false;
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

    public boolean AddDiscountUnusedPeriod(String startDate, String endDate, String productNameToUpdate, String discountToSetDate) {
        connect();

        try {
            int discountCode = 0;

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT discount_code\n" +
                    "FROM discounts\n" +
                    "WHERE discount_reason = '" + discountToSetDate + "';");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                discountCode = rs.getInt(1);
            }

            String query =
                    """
                            UPDATE products
                            SET discount_code = ?
                            WHERE product_name = ?;""";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBigDecimal(1, new BigDecimal(discountCode));
            preparedStatement.setString(2, productNameToUpdate);

            preparedStatement.execute();

            PreparedStatement stmt2 = connection.prepareStatement(
                    "SELECT product_id\n" +
                        "FROM products\n" +
                        "WHERE product_name = '" + productNameToUpdate + "';");

            ResultSet rs1 = stmt2.executeQuery();

            int productCode = 0;

            while(rs1.next()){
                productCode = rs1.getInt(1);
            }

            String addToUsedDiscounts =
                    """
                        INSERT INTO used_discounts (used_discount_id, used_discount_startdate, used_discount_enddate, product_id) 
                        VALUES (?, ?, ?, ?)""";

            PreparedStatement preparedStatement1 = connection.prepareStatement(addToUsedDiscounts);
            preparedStatement1.setBigDecimal(1, new BigDecimal(discountCode));
            preparedStatement1.setDate(2, Date.valueOf(startDate));
            preparedStatement1.setDate(3, Date.valueOf(endDate));
            preparedStatement1.setBigDecimal(4, new BigDecimal(productCode));

            preparedStatement1.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Discount to product added!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "This product is already assigned to this discount");
            e.printStackTrace();
        }
        disconnect();
        return false;
    }

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
}
