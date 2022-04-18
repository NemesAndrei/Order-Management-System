package org.example.Model;

/**
 * @author Nemes Mihnea-Andrei
 * Product
 * represents the class that corresponds to the Product table in the database
 * @since 27 Apr, 2021
 */

public class Product {
    int id;
    int stock;
    String name;
    int price;

    public Product() {
    }

    /**
     * Product
     * constructor for product with all parameters
     * @param id
     * @param stock
     * @param name
     * @param price
     */
    public Product(int id, int stock, String name, int price) {
        super();
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.price = price;
    }

    /**
     * Product
     * constructor for product without id
     * @param stock
     * @param name
     * @param price
     */
    public Product(int stock, String name, int price) {
        super();
        this.stock = stock;
        this.name = name;
        this.price = price;
    }

    /**
     * getId
     * getter for product id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * setId
     * setter for product id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getStock
     * getter for product stock
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * setStock
     * setter for product stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * getName
     * getter for product name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * setter for product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPrice
     * getter for product price
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * setPrice
     * setter for product price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
