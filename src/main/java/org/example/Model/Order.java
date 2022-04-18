package org.example.Model;

/**
 * @author Nemes Mihnea-Andrei
 * Order
 * represents the class that corresponds to the Order table in the database
 * @since 27 Apr, 2021
 */

public class Order {
    int id;
    int clientId;
    int productId;
    int quantity;

    public Order() {
    }

    /**
     * Order
     * constructor for order with all parameters
     * @param id
     * @param clientId
     * @param productId
     * @param quantity
     */
    public Order(int id, int clientId, int productId, int quantity) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.clientId = clientId;
        this.productId = productId;
    }

    /**
     * Order
     * constructor for order without id parameter
     * @param clientId
     * @param productId
     * @param quantity
     */
    public Order(int clientId, int productId, int quantity) {
        super();
        this.quantity = quantity;
        this.clientId = clientId;
        this.productId = productId;
    }

    /**
     * getId
     * getter for order id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * setId
     * setter for order id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getClientId
     * getter for order's client id
     * @return int
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * setClientId
     * setter for order's client id
     * @param clientid
     */
    public void setClientId(int clientid) {
        this.clientId = clientid;
    }

    /**
     * getProductId
     * getter for order's product id
     * @return int
     */
    public int getProductId() {
        return productId;
    }

    /**
     * setProductId
     * setter for order's product id
     * @param productid
     */
    public void setProductId(int productid) {
        this.productId = productid;
    }

    /**
     * getQuantity
     * getter for quantity
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setQuantity
     * setter for quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
