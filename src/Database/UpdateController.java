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
