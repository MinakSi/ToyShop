package com.minakov.database;

import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.Status;
import com.minakov.database.entity.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  This class realizes manipulations with DB. It makes any query executions or updates.
 *  Singleton.
 *
 * @author Serhii Minakov
 */

public class DBManager {

    private static DBManager dbManager;
    private static final String INTERRUPT = "Interrupted!";
    @Resource(name = "jdbc/toyshop_db")
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    private static final String SQL_FIND_USER_BY_PHONE = "SELECT * FROM user inner join user_type on " +
            "user.type_id = user_type.id WHERE phone_number = ?";
    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
    private static final String SQL_FIND_ALL_ORDERS = "select phone_number, date, `order`.id, status_id, name, invoice_number, total from `order` inner join order_status os on `order`.status_id = os.id join user u on u.id = `order`.user_id";
    private static final String SQL_FIND_USER_ORDERS = "select phone_number, date, `order`.id, status_id, name, invoice_number, total from `order` inner join order_status os on `order`.status_id = os.id join user u on u.id = `order`.user_id " +
            "where user_id = ?;";
    private static final String SQL_INSERT_INVOICE = "UPDATE `order` SET invoice_number = ? WHERE id = ?";
    private static final String SQL_UPDATE_STATUS = "update `order` set status_id = ? where id = ?;";
    private static final String SQL_FIND_ORDER_DETAILS = "select product_id, op.price, amount, name " +
        "from order_product op join product p on p.id = op.product_id where order_id = ?;";

    private static final String SQL_CREATE_USER = "INSERT INTO user(first_name, second_name, email, phone_number, address, password) " +
            "values(?, ?, ?, ?, ?, ?);";
    private static final String SQL_CREATE_ORDER = "INSERT INTO `order` (user_id,  total) values (?, 0);";
    private static final String SQL_FIND_NEW_ORDER = "select id from `order` where user_id=? and total=0;";
    private static final String SQL_CREATE_ORDER_PRODUCT = "insert into order_product (product_id, order_id, amount, price)" +
            "values (?,?,?,0);";
    private static final String SQL_FIND_ORDER = "select phone_number, date,u.first_name, u.second_name,`order`.id, status_id, name, invoice_number, total from `order` inner join order_status os on `order`.status_id = os.id\n" +
            "    join user u on u.id = `order`.user_id where `order`.id = ?;";
    private static final String SQL_BLOCK_USER = "update user set type_id = 3 where id = ?;";
    private static final String SQL_CREATE_PRODUCT = "INSERT INTO product" +
            "    (name, description, price, amount_on_storage) VALUES (?, ?, ?, ?);";
    public static final String SQL_ORDER_BY_PRICE = " ORDER BY price";
    public static final String SQL_ORDER_BY_PRICE_DESC = " ORDER BY price DESC";
    public static final String SQL_ORDER_BY_NAME = " ORDER BY name";
    public static final String SQL_ORDER_BY_NAME_DESC = " ORDER BY name DESC";
    public static final String SQL_ORDER_BY_ID = " ORDER BY id";
    private static final String SQL_LIMIT = " LIMIT ?, ?";
    private static final String IMAGE_PATH = "C:\\Users\\Lenovo\\IdeaProjects\\finalProject\\src\\main\\webapp\\view\\img";


    private DBManager() {
    }


    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }


    public Connection getConnection() throws SQLException, NamingException {
        Context context = new InitialContext();
        ds = (DataSource) context.lookup("java:comp/env/jdbc/toyshop_db");

        return ds.getConnection();
    }

    /**
     * Gives you a user row from DB.
     *
     * @param phone phone number of the user, you want to get
     * @return User object
     * @throws SQLException if failure
     * @see User
     */
    public User getUser(String phone) throws SQLException {
        User user = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_PHONE)) {
            statement.setString(1, phone);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                user = new User(rs.getInt("user.id"),
                        new Status(rs.getInt("user_type.id"),
                                rs.getString("type")),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email"));
            }
        } catch (NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return user;
    }
    /**
     * Inserts a user row to DB.
     *
     * @param phone phone number of the user, you want to get
     * @throws SQLException if failure
     * @see User
     */
    public void setUser(String name, String secondName, String email, String phone, String address, String pass) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, name);
            statement.setString(2, secondName);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.setString(6, pass);
            statement.executeUpdate();
        } catch (NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
    }

    /**
     * Gives you some product rows from DB.
     *
     * @param order sorting order
     * @param limitLow how many rows will be skipped after query
     * @param limitMax how many rows will be returned
     * @return ArrayList of Product
     * @see Product
     */

    public ArrayList<Product> getProducts(String order, int limitLow, int limitMax) {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS+order+SQL_LIMIT)) {
            statement.setInt(1, limitLow);
            statement.setInt(2, limitMax);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("amount_on_storage"),
                            rs.getString("description")));
                }
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return products;
    }

    /**
     * Gives you a product row from DB
     *
     * @param id - id of product, you want to be selected
     * @return Product object
     * @see Product
     */

    public Product getProduct(String id) {
        Product product = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCT_BY_ID)) {
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("amount_on_storage"),
                        rs.getString("description"));
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return product;
    }

    /**
     * Gives you an Order row from DB
     *
     * @param id - id of an order, you want to be selected
     * @return Order object
     * @see Order
     */

    public Order getOrder(String id) {
        Order order = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER)) {
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                User user = getUser(rs.getString("phone_number"));
                Date date = rs.getDate("date");
                order = new Order(rs.getInt("id"),
                        date.toLocalDate(),
                        new Status(rs.getInt("status_id"),
                                rs.getString("name")),
                        user,
                        rs.getString("invoice_number"),
                        rs.getDouble("total")
                );
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return order;
    }

    /**
     * Inserts new Order and order_product rows to DB.
     * Includes transaction.
     *
     * @param userId - id of user, whose order it is
     * @param products - Map, which consists of product id and amount
     *                 of this product in the order.
     * @throws SQLException if failure
     */
    public void setOrder(int userId, Map<Integer, Integer> products) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER);
            statement.setInt(1, userId);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_FIND_NEW_ORDER);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int orderId = rs.getInt("id");
            for (Map.Entry<Integer, Integer> p : products.entrySet()) {
                statement = connection.prepareStatement(SQL_CREATE_ORDER_PRODUCT);
                statement.setInt(1, p.getKey());
                statement.setInt(2, orderId);
                statement.setInt(3, p.getValue());
                statement.executeUpdate();
            }
            connection.commit();

        } catch (SQLException | NamingException e) {
            connection.rollback();
            logger.log(Level.WARNING, INTERRUPT, e);
            throw new SQLException();
        } finally {
            connection.close();
        }
    }

    /**
     * Inserts new Product into DB.
     * Puts an image of the product into direct package,
     * described in static constant IMAGE_PATH
     *
     * @param part  a form item that was received within a multipart/form-data POST request.
     * @param product - Product object, which will be inserted
     * @throws SQLException if failure
     * @see Product
     */
    public void setProduct(Part part, Product product) throws SQLException {
        Connection connection = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getAmountOnStorage());
            statement.executeUpdate();
            String fileName = URLDecoder.decode(part.getSubmittedFileName(), "UTF-8");
            File uploads = new File(IMAGE_PATH + File.separator + fileName);
            uploads.getParentFile().mkdirs();
            InputStream fileContent = part.getInputStream();
            java.nio.file.Files.copy(fileContent, uploads.toPath());
            fileContent.close();
            connection.commit();

        } catch (IOException | SQLException | NamingException e){
            connection.rollback();
            throw new SQLException();
        } finally {
            connection.close();
        }

    }

    /**
     * Inserts a new invoice number to existed order into DB
     *
     * @param id - order id
     * @param invoice - invoice number
     * @throws SQLException if failure
     */
    public void setInvoice(int id, String invoice) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INVOICE);
            statement.setString(1, invoice);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Blocks a user on DB level.
     *
     * @param id - id of user, who will be bocked
     * @throws SQLException if failure
     */
    public void blockUser(int id) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_BLOCK_USER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets new order status to an order in DB.
     *
     * @param id - order id
     * @param status - status id
     * @throws SQLException if failure
     */

    public void updateOrderStatus(int id, int status) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS);
            statement.setInt(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all user's orders
     *
     * @param userId - id of a user
     * @return Array List of Orders
     * @see Order
     * @throws SQLException if failure
     */

    public ArrayList<Order> getUserOrders(int userId) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ORDERS)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = getUser(rs.getString("phone_number"));
                    Date date = rs.getDate("date");
                    orders.add(new Order(rs.getInt("id"),
                            date.toLocalDate(),
                            new Status(rs.getInt("status_id"),
                                    rs.getString("name")),
                            user,
                            rs.getString("invoice_number"),
                            rs.getDouble("total")
                            ));
                }
            }
        } catch (NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return orders;
    }
    /**
     * Gets all the existed orders
     *
     * @return Array List of Orders
     * @see Order
     * @throws SQLException if failure
     */
    public ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = getUser(rs.getString("phone_number"));
                    Date date = rs.getDate("date");
                    orders.add(new Order(rs.getInt("id"),
                            date.toLocalDate(),
                            new Status(rs.getInt("status_id"),
                                    rs.getString("name")),
                            user,
                            rs.getString("invoice_number"),
                            rs.getDouble("total")
                    ));
                }
            }
        } catch (NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return orders;
    }

    /**
     * Gets products, which are included in an order
     *
     * @param orderId - id of an order
     * @return Array List of Products
     * @see Product
     */

    public ArrayList<Product> getOrderDetails(int orderId) {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_DETAILS)) {
            statement.setInt(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getDouble("op.price"),
                            rs.getInt("amount")));
                }
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return products;
    }

}
