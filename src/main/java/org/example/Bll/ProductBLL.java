package org.example.Bll;

import org.example.Bll.Validators.PriceValidator;
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
 * ProductBLL
 * it checks if the input is valid and implements the product operations
 */
public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * constructor
     * it initializes the productDAO and the list of validators
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        productDAO = new ProductDAO();
    }

    /**
     * findById
     * returns the product with the id given as a parameter, from the database
     *
     * @param id
     * @return Product
     */
    public Product findById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * findAll
     * returns a list containing all the products in the database
     *
     * @return List<Product>
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        products = productDAO.findAll();
        if (products == null) {
            JOptionPane.showMessageDialog(null, "Operation cannot be performed", "Error message", JOptionPane.WARNING_MESSAGE);
        }
        return products;
    }

    /**
     * insert
     * it inserts into the database a new product given as a parameter, returns true if the insert was successful and false otherwise
     *
     * @param product
     * @return boolean
     * @throws IllegalAccessException
     */
    public boolean insert(Product product) throws IllegalAccessException {
        for (Validator v : validators) {
            if (!v.validate(product)) {
                return false;
            }
        }
        productDAO.insert(product);
        return true;
    }

    /**
     * delete
     * it deletes a product with id given as a parameter and also all the orders associated with the specified product
     *
     * @param id
     */
    public void delete(int id) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.specialOrderDelete(id, "Product");
        productDAO.delete(id);
    }

    /**
     * update
     * it updates the fields of a product with id given as a parameter, with the values specified in the product also given as a parameter, returns true if this was successful and false otherwise
     *
     * @param id
     * @param product
     * @return boolean
     * @throws IllegalAccessException
     */
    public boolean update(int id, Product product) throws IllegalAccessException {
        for (Validator v : validators) {
            if (!v.validate(product)) {
                return false;
            }
        }
        productDAO.update(product, id);
        return true;
    }

}
