package org.example.Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Bll.ProductBLL;
import org.example.Dao.ProductDAO;
import org.example.Model.Product;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 * Product GUI Controller
 * implements the GUI for the Product operations
 * @since 27 Apr, 2021
 */

public class ProductController implements Initializable {

    ProductBLL productBLL = new ProductBLL();
    Product product = new Product();
    ProductDAO productDAO = new ProductDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * initializeAll
     * clears the screen of the Product GUI
     */
    public void initializeAll() {
        productTable.getColumns().clear();
        ObservableList<TableColumn<Product, ?>> tableColumns = productDAO.getColumnsHead(product);
        productTable.getColumns().addAll(tableColumns);
        productTable.setVisible(false);
        productInsertLabel.setVisible(false);
        productStockLabel.setVisible(false);
        productNameLabel.setVisible(false);
        productPriceLabel.setVisible(false);
        productStockText.setVisible(false);
        productNameText.setVisible(false);
        productPriceText.setVisible(false);
        insertProductButton.setVisible(false);
        updateProductCombo.setVisible(false);
        updateProductCombo.getItems().clear();
        ChooseProductUpdateB.setVisible(false);
        productStockUpdate.setVisible(false);
        productNameUpdate.setVisible(false);
        productPriceUpdate.setVisible(false);
        productStockUpdateT.setVisible(false);
        productNameUpdateT.setVisible(false);
        productPriceUpdateT.setVisible(false);
        updateProductB.setVisible(false);
        deleteProductCombo.setVisible(false);
        deleteProductCombo.getItems().clear();
        deleteProductB.setVisible(false);
        updateProductLabel.setVisible(false);
    }

    boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @FXML
    private Button viewProducts;

    @FXML
    private Button addProduct;

    @FXML
    private Button editProduct;

    @FXML
    private Button deleteProduct;

    @FXML
    private Label productMenuLabel;

    @FXML
    private TableView productTable;

    @FXML
    private Label productInsertLabel;

    @FXML
    private Label productStockLabel;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label productPriceLabel;

    @FXML
    private TextField productStockText;

    @FXML
    private TextField productNameText;

    @FXML
    private TextField productPriceText;

    @FXML
    private Button insertProductButton;

    @FXML
    private ComboBox updateProductCombo;

    @FXML
    private Button ChooseProductUpdateB;

    @FXML
    private Label productStockUpdate;

    @FXML
    private Label productNameUpdate;

    @FXML
    private Label productPriceUpdate;

    @FXML
    private TextField productStockUpdateT;

    @FXML
    private TextField productNameUpdateT;

    @FXML
    private TextField productPriceUpdateT;

    @FXML
    private Button updateProductB;

    @FXML
    private ComboBox deleteProductCombo;

    @FXML
    private Button deleteProductB;

    @FXML
    private Label updateProductLabel;

    @FXML
    void chooseProduct(ActionEvent event) {

    }

    /**
     * chooseProduct1
     * initializes the elements needed to update a product
     *
     * @param event
     */
    @FXML
    void chooseProduct1(ActionEvent event) {
        Product tempProduct = productBLL.findById((Integer) updateProductCombo.getValue());
        productStockUpdateT.setText(String.valueOf(tempProduct.getStock()));
        productNameUpdateT.setText(tempProduct.getName());
        productPriceUpdateT.setText(String.valueOf(tempProduct.getPrice()));
        productStockUpdate.setVisible(true);
        productNameUpdate.setVisible(true);
        productPriceUpdate.setVisible(true);
        productStockUpdateT.setVisible(true);
        productNameUpdateT.setVisible(true);
        productPriceUpdateT.setVisible(true);
        updateProductB.setVisible(true);
    }

    /**
     * deleteProduct
     * initializes the elements needed to delete a product
     *
     * @param event
     */
    @FXML
    void deleteProduct(ActionEvent event) {
        initializeAll();
        deleteProductB.setVisible(true);
        updateProductLabel.setVisible(true);
        deleteProductCombo.setVisible(true);
        List<Product> productList = productBLL.findAll();
        for (Product product : productList) {
            deleteProductCombo.getItems().add(product.getId());
        }
    }

    @FXML
    void deleteProduct1(ActionEvent event) {
        productBLL.delete((Integer) deleteProductCombo.getValue());
    }

    @FXML
    void deleteProduct2(ActionEvent event) {

    }

    /**
     * insertProduct
     * initializes the elements needed to insert a product
     *
     * @param event
     */
    @FXML
    void insertProduct(ActionEvent event) {
        initializeAll();
        productInsertLabel.setVisible(true);
        productStockLabel.setVisible(true);
        productNameLabel.setVisible(true);
        productPriceLabel.setVisible(true);
        productStockText.setVisible(true);
        productNameText.setVisible(true);
        productPriceText.setVisible(true);
        insertProductButton.setVisible(true);
    }

    /**
     * insertProductDB
     * performs the insert operation of a product into the DB
     *
     * @param event
     */
    @FXML
    void insertProductDB(ActionEvent event) throws IllegalAccessException {
        if (isNumeric(productStockText.getText()) && isNumeric(productPriceText.getText())) {
            if (!productBLL.insert(new Product(Integer.parseInt(productStockText.getText()), productNameText.getText(), Integer.parseInt(productPriceText.getText())))) {
                JOptionPane.showMessageDialog(null, "Invalid input data for product to be inserted into DB", "Error message", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Product was inserted successfully", "Error message", JOptionPane.INFORMATION_MESSAGE);
                productStockText.setText("");
                productNameText.setText("");
                productPriceText.setText("");
            }
        } else
            JOptionPane.showMessageDialog(null, "The stock and price must be integers", "Error message", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * listAllProducts
     * populates the Products table in order to be able to view them in the GUI
     *
     * @param event
     */
    @FXML
    void listAllProducts(ActionEvent event) {
        initializeAll();
        productTable.setVisible(true);
        ObservableList<Product> allClients = FXCollections.observableArrayList(productDAO.findAll());
        productTable.setItems(allClients);
    }

    @FXML
    void showProductsTable(ActionEvent event) {

    }

    /**
     * updateProduct
     * initializes the elements needed to update a product
     *
     * @param event
     */
    @FXML
    void updateProduct(ActionEvent event) {
        initializeAll();
        updateProductLabel.setVisible(true);
        updateProductCombo.setVisible(true);
        ChooseProductUpdateB.setVisible(true);
        List<Product> clientList = productBLL.findAll();
        for (Product client : clientList) {
            updateProductCombo.getItems().add(client.getId());
        }
    }

    /**
     * updateProduct1
     * performs the update operation of a product into the DB
     *
     * @param event
     */
    @FXML
    void updateProduct1(ActionEvent event) throws IllegalAccessException {
        if (isNumeric(productStockUpdateT.getText()) && isNumeric(productPriceUpdateT.getText())) {
            productBLL.update((Integer) updateProductCombo.getValue(), new Product(Integer.parseInt(productStockUpdateT.getText()), productNameUpdateT.getText(), Integer.parseInt(productPriceUpdateT.getText())));
        } else
            JOptionPane.showMessageDialog(null, "The stock and price must be integers", "Error message", JOptionPane.WARNING_MESSAGE);
    }


}
