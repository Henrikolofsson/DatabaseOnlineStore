package Database;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateController {
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

    public static void updateStock(int productId, int orderId, int quantity) {
        connect();

        try {
            String update = "UPDATE stock SET amount = ? " +
                    "WHERE product_id = ? " +
                    "AND order_id = ?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.setInt(3, orderId);
            statement.executeUpdate();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void placeOrder(int orderId) {
        connect();

        try {
            String update = "UPDATE orders SET status = ? " +
                    "WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, "PENDING");
            statement.setInt(2, orderId);
            statement.executeUpdate();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void confirmOrder(int itemValue) {
        connect();

        try {
            String update = "UPDATE orders SET status = ? " +
                    "WHERE id = ?;" +
                    "UPDATE stock SET status = ? " +
                    "WHERE order_id = ?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, "SHIPPED");
            statement.setInt(2, itemValue);
            statement.setString(3, "SHIPPED");
            statement.setInt(4, itemValue);
            statement.executeUpdate();

            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProductToShoppingCart(int newQuantity, int productID) {
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    """
                            UPDATE products
                            SET product_quantity = ?
                            WHERE product_id = ?"""
            );
            stmt.setBigDecimal(1, new BigDecimal(newQuantity));
            stmt.setBigDecimal(2, new BigDecimal(productID));
            stmt.executeUpdate();

            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        /*try {
            PreparedStatement stmt = connection.prepareStatement(
                    """

                            """
            );
            stmt.setBigDecimal(1, new BigDecimal(newQuantity));
            stmt.setBigDecimal(2, new BigDecimal(productID));
            ResultSet rs = stmt.executeQuery();

            stmt.close();
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }*/
    }

    /*public boolean updateQuantity(int newQuantity, String productNameToUpdate) {
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
    }*/
}
