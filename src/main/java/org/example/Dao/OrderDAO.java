package org.example.Dao;

import org.example.Connection.ConnectionFactory;
import org.example.Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * @author Nemes Mihnea-Andrei
 * OrderDAO
 * extends the AbstractDAO class
 * @since 27 Apr, 2021
 */

public class OrderDAO extends AbstractDAO<Order> {

    /**
     * specialOrderDelete
     * it deletes the orders that belong to a client or product that is to be deleted, specified by the id parameter
     *
     * @param id
     * @param s
     */
    public void specialOrderDelete(int id, String s) {
        String from;
        if (s.equals("Client")) {
            from = "clientid";
        } else {
            from = "productid";
        }
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM assign3.Order WHERE " + from + "=" + id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Order" + "DAO:SpecialDelete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
