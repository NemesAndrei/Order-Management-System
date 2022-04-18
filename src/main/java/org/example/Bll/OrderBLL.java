package org.example.Bll;

import org.example.Bll.Validators.Validator;
import org.example.Dao.OrderDAO;
import org.example.Dao.ProductDAO;
import org.example.Model.Order;
import org.example.Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Nemes Mihnea-Andrei
 * OrderBLL
 * checks whether the input for the Order operations is valid
 * @since 27 Apr, 2021
 */
public class OrderBLL {

    private List<Validator<Order>> validators;
    private OrderDAO orderDAO;

    /**
     * constructor
     * it initializes the orderDAO object
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * findById
     * returns the order with the id given as a parameter, from the database
     *
     * @param id
     * @return Order
     */
    public Order findById(int id) {
        Order order = orderDAO.findById(id);
        if (order == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return order;
    }

    /**
     * findAll
     * returns a list containing all the orders in the database
     *
     * @return List<Order>
     */
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        orders = orderDAO.findAll();
        if (orders == null) {
            throw new NoSuchElementException("The operation could not be performed");
        }
        return orders;
    }

    /**
     * insert
     * it inserts into the database a new order given as a parameter, returns true if the insert was successful and false otherwise
     * it also checks whether there is enough stock of the specified product to add to the order
     *
     * @param order
     * @return boolean
     * @throws IllegalAccessException
     */
    public boolean insert(Order order) throws IllegalAccessException {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(order.getProductId());
        if (order.getQuantity() <= 0) {
            JOptionPane.showMessageDialog(null, "Order Quantity cannot be smaller than 0", "Error message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (product.getStock() < order.getQuantity()) {
            JOptionPane.showMessageDialog(null, "Order Quantity cannot be greater than the product's available stock", "Error message", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            product.setStock(product.getStock() - order.getQuantity());
            productDAO.update(product, order.getProductId());
            orderDAO.insert(order);
        }
        return true;
    }

    /**
     * delete
     * it deletes an order with id given as a parameter
     *
     * @param id
     */
    public void delete(int id) throws IllegalAccessException {
        Order order = orderDAO.findById(id);
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(order.getProductId());
        product.setStock(product.getStock() + order.getQuantity());
        productDAO.update(product, order.getProductId());
        orderDAO.delete(id);
    }

    /**
     * update
     * it updates the order with id given as a parameter, with the values of the order also given as a parameter
     *
     * @param id
     * @param order
     * @throws IllegalAccessException
     */
    public void update(int id, Order order) throws IllegalAccessException {
        orderDAO.update(order, id);
    }
}
