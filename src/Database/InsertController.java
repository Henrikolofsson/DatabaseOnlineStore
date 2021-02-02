package Database;

import Entities.*;

import java.sql.*;

public class InsertController {
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
        ----------------------------------------------------------------------------------------------------------
        ***************************************USER RELATED*******************************************************
        ----------------------------------------------------------------------------------------------------------
     */

        /*
        CREATES A USER IN THE USERS TABLE
     */

    protected static boolean createUser(DatabaseController dbController, User user, boolean isAdmin) {
        connect();


        try {
            String insert_user = "INSERT INTO users(ID, user_name, password, first_name, last_name, email, address, city, country, phone, role) " +
                    "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(insert_user);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getCity());
            statement.setString(8, user.getCountry());
            statement.setString(9, user.getPhone());

            if(isAdmin) {
                statement.setInt(10, 1);
            } else {
                statement.setInt(10, 2);
            }

            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        disconnect();
      return true;
    }

    public static void addSupplier(Supplier supplier) {
        connect();

        try {
            String query = "INSERT INTO supplier(id, name, phone, address, city, country) VALUES(DEFAULT, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getSupplierPhone());
            statement.setString(3, supplier.getSupplierAddress());
            statement.setString(4, supplier.getSupplierCity());
            statement.setString(5, supplier.getSupplierCountry());
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public static void addDiscount(Discount discount) {
        connect();

        try {
            String query = "INSERT INTO discount(id, reason, percentage, code, start_date, end_date) VALUES(DEFAULT, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, discount.getDiscountReason());
            statement.setInt(2, discount.getDiscountPercentage());
            statement.setInt(3, discount.getDiscountCode());
            statement.setDate(4, discount.getDiscountStart());
            statement.setDate(5, discount.getDiscountEnd());
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public static int addProduct(Product product) {
        connect();

        try {
            int productId = -1;
            String query = "INSERT INTO products(id, supplier_id, price, name, status) VALUES(DEFAULT, ?, ?, ?, ?) RETURNING id;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, product.getProductSupplier());
            statement.setInt(2, product.getProductPrice());
            statement.setString(3, product.getProductName());
            statement.setString(4, product.getProductStatus());
            statement.execute();

            ResultSet productIdSet = statement.getResultSet();
            while(productIdSet.next()) {
                productId = productIdSet.getInt(1);
            }

            productIdSet.close();
            statement.close();
            disconnect();
            return productId;

        } catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int addOrder(Order order) {
        connect();

        try {
            int orderId = -1;
            String query = "INSERT INTO orders(id, user_id, date, status) VALUES(DEFAULT, ?, ?, ?) RETURNING id;";
            System.out.println(query);
            System.out.println(order);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, order.getUserId());
            statement.setDate(2, order.getDate());
            statement.setString(3, order.getStatus());

            statement.execute();
            ResultSet orderIdSet = statement.getResultSet();
            while(orderIdSet.next()) {
                orderId = orderIdSet.getInt(1);
            }

            orderIdSet.close();
            statement.close();
            disconnect();

            return orderId;
        } catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void addOrderRow(OrderRow orderRow) {
        connect();

        try {
            String insert = "INSERT INTO order_row(id, order_id, product_id, quantity) VALUES(DEFAULT, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);

            statement.setInt(1, orderRow.getOrderId());
            statement.setInt(2, orderRow.getProductId());
            statement.setInt(3, orderRow.getQuantity());
            statement.execute();

            statement.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStock(Stock stock) {
        connect();

        try {
            String insert = "INSERT INTO stock(id, product_id, date, order_id, amount, status) VALUES(DEFAULT, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);

            statement.setInt(1, stock.getProductId());
            statement.setDate(2, stock.getDate());
            statement.setInt(3, stock.getOrderId());
            statement.setInt(4, stock.getAmount());
            statement.setString(5, stock.getStatus());
            statement.execute();

            statement.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void reserveProduct(int orderId, int productId, int quantity, Date dateToday) {
        connect();
        System.out.println(orderId);
        System.out.println(productId);
        System.out.println(quantity);

        try {
            String insert = "INSERT INTO stock(id, product_id, date, order_id, amount, status) VALUES(DEFAULT, ?, ?, ?, ?, ?) RETURNING id;";

            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setInt(1, productId);
            statement.setDate(2, dateToday);
            statement.setInt(3,orderId);
            statement.setInt(4, quantity);
            statement.setString(5, "RES");

            statement.execute();
            statement.close();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProductDiscount(int productId, int discountId) {
        connect();

        try {
            String insert = "INSERT INTO assigned_discounts(id, discount_id, product_id) VALUES(DEFAULT, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setInt(1, discountId);
            statement.setInt(2, productId);

            statement.execute();
            disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
