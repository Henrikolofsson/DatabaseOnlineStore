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
               System.out.println("Password: " + rs.getString(1) + " ");
               System.out.println(password);
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
               System.out.println("Username Checked: " + rs.getString(1) + " ");
               System.out.println("Username entered: " + username);
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
               System.out.println("Username Checked: " + rs.getString(1) + " ");
               System.out.println("Username entered: " + username);
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

    public boolean deleteProduct(String productNameToDelete) {
        connect();

        try {
            System.out.println("STRING TO DELETE: " + productNameToDelete);
            String query =
                    "DELETE FROM products WHERE LOWER(product_name) LIKE ?";

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

    public ArrayList<String> getSearchedProducts(String searchedCode, String searchedSupplier, String searchedProduct) {
        ArrayList<String> searchedProducts = new ArrayList<>();
        connect();
        try {
            String query =
                    "SELECT product_id, product_name, product_quantity, product_price, product_supplier FROM products\n" +
                            "WHERE product_id = ?\n" +
                            "  OR LOWER(product_name) LIKE ?\n" +
                            "  OR LOWER(product_supplier) LIKE ?\n" +
                            "  ORDER BY product_id ASC";

            PreparedStatement stmt = connection.prepareStatement(query);

            if(searchedCode == null){
                stmt.setNull(1, Types.INTEGER);
                stmt.setString(2, "%" + searchedSupplier.toLowerCase() + "%");
                stmt.setString(3,  "%" + searchedProduct.toLowerCase() + "%");
            } else {
                int codeInt = Integer.parseInt(searchedCode);
                stmt.setBigDecimal(1, new BigDecimal(codeInt));
                stmt.setNull(2, Types.VARCHAR);
                stmt.setNull(3, Types.VARCHAR);
            }

            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()) {
                String productToAdd =
                        "Code: "+ rs.getString(1) + " | Name: " + rs.getString(2) + " | Quantity: " + rs.getString(3) +
                        " | Base Price: " + rs.getString(4) + ":- | Supplier: " + rs.getString(5);
                searchedProducts.add(i, productToAdd);
                i++;
            }

            stmt.close();
            rs.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return searchedProducts;

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
                System.out.println("RESULT: " + rs.getString(3));
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

    public boolean AddDiscountPeriod(String startDate, String endDate, String productNameToUpdate, String discountToSetDate) {
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

            String addDiscountToProductQuery =
                    "UPDATE discounts\n" +
                    "SET discount_startdate = ?, discount_enddate = ?\n" +
                    "WHERE discount_code = ?;\n" +
                    "UPDATE products\n" +
                    "SET discount_code = ?\n" +
                    "WHERE product_name = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(addDiscountToProductQuery);
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            preparedStatement.setBigDecimal(3, new BigDecimal(discountCode));
            preparedStatement.setBigDecimal(4, new BigDecimal(discountCode));
            preparedStatement.setString(5, productNameToUpdate);

            preparedStatement.execute();
            connection.close();
            disconnect();
            JOptionPane.showMessageDialog(null, "Discount to product added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return false;
    }
}
