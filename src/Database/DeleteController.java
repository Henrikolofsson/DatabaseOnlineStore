package Database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteController {
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

    public static void deleteProduct(int product) {
        connect();

        try {
            String changeProductStatus = "UPDATE products SET status = 'Utgått' WHERE id = '" + product + "';";
            System.out.println(changeProductStatus);
            PreparedStatement statement = connection.prepareStatement(changeProductStatus);

            statement.executeUpdate();
            statement.close();
            disconnect();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        PRODUCT RELATED
     */
  /*  public boolean deleteProduct(String productNameToDelete) {
        connect();

        *//* TODO:
        KUNNA TA BORT PRODUKT ÄVEN OM DISCOUNT ÄR APPLICERAD. DETTA GÅR I NULÄGET INTE PGA ATT
        used_discounts ANVÄNDER PRODUCT_ID SOM PRIMARY KEY. KAN ENKELT FIXAS MED ATT HÄMTA ID FRÅN products OCH
        APPLICERA DEN PÅ used_discounts. FRÅGAN ÄR: SKA 'BORTTAGNA' PRODUKTER SYNAS NÄR MAN KOLLAR GAMLA PRODUKTERS
        DISCOUNT HISTORY? MAN HADE KUNANT SPARA PRODUCT_ID SOM EN SEPARAT KOLUMN MEN DÅ BLIR DET ÖVERFLÖDIG DATA
        *//*

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
    }*/
}
