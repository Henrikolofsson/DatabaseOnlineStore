package Database;

import Entities.User;

import java.sql.*;

public class DatabaseController {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/onlinestore";
    private static String user = "postgres";
    private static String password = "123Henrik123";
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
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin WHERE EXISTS (SELECT admin_password);");
           ResultSet rs = statement.executeQuery();

           while(rs.next()) {
               System.out.println(rs.getString(1) + " " + rs.getString(2));
               System.out.println(password);
               if(rs.getString(2).equals(password)) {
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

    public boolean createAdminUser(User user) {
        connect();

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(user_id, user_name, user_last_name, user_password, email, address, city, country, phone, role) " +
                    "VALUES(DEFAULT, '" + user.getUserName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getAddress() + "', '" +
                    user.getCity() + "', '" + user.getCountry() + "', '" + user.getPhone() + "', 1);");

            statement.execute();
            statement.close();
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

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(user_id, user_name, user_last_name, user_password, email, address, city, country, phone, role) " +
                    "VALUES(DEFAULT, '" + user.getUserName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getAddress() + "', '" +
                    user.getCity() + "', '" + user.getCountry() + "', '" + user.getPhone() + "', 2);");

            statement.execute();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
