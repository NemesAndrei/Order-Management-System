package org.example.Presentation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Bll.ClientBLL;
import org.example.Bll.OrderBLL;
import org.example.Bll.ProductBLL;
import org.example.Dao.ClientDAO;
import org.example.Dao.OrderDAO;
import org.example.Model.Client;
import org.example.Model.Order;
import org.example.Model.Product;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 * Order GUI Controller
 * implements the GUI for the Order operations
 * @since 27 Apr, 2021
 */

public class OrderController implements Initializable {

    OrderDAO orderDAO = new OrderDAO();
    Order order = new Order();
    OrderBLL orderBLL = new OrderBLL();
    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * initializeAll
     * clears the screen of the Order GUI
     */
    public void initializeAll() {
        ordersTable.getColumns().clear();
        ObservableList<TableColumn<Order, ?>> tableColumns = orderDAO.getColumnsHead(order);
        ordersTable.getColumns().addAll(tableColumns);
        ordersTable.setVisible(false);
        selectClientCombo.setVisible(false);
        selectClientCombo.getItems().clear();
        selectProductCombo.setVisible(false);
        selectProductCombo.getItems().clear();
        selectClientLabel.setVisible(false);
        selectProductLabel.setVisible(false);
        selectQuantityLabel.setVisible(false);
        selectQuantityT.setText("");
        selectQuantityT.setVisible(false);
        insertOrderButton.setVisible(false);
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
    private Label orderMenuLabel;

    @FXML
    private Button showOrders;

    @FXML
    private Button addOrder;

    @FXML
    private Button generateBills;

    @FXML
    private TableView ordersTable;

    @FXML
    private ComboBox selectClientCombo;

    @FXML
    private ComboBox selectProductCombo;

    @FXML
    private Label selectClientLabel;

    @FXML
    private Label selectProductLabel;

    @FXML
    private Label selectQuantityLabel;

    @FXML
    private TextField selectQuantityT;

    @FXML
    private Button insertOrderButton;

    /**
     * createOrder
     * initializes the elements needed to insert an order
     *
     * @param event
     */
    @FXML
    void createOrder(ActionEvent event) {
        initializeAll();
        selectClientLabel.setVisible(true);
        ClientBLL clientBLL = new ClientBLL();
        List<Client> clientList = clientBLL.findAll();
        for (Client client : clientList) {
            selectClientCombo.getItems().add(client.getId());
        }
        selectClientCombo.setVisible(true);
        ProductBLL productBLL = new ProductBLL();
        List<Product> productList = productBLL.findAll();
        for (Product product : productList) {
            selectProductCombo.getItems().add(product.getId());
        }
        selectProductCombo.setVisible(true);
        selectProductLabel.setVisible(true);
        selectQuantityLabel.setVisible(true);
        selectQuantityT.setVisible(true);
        insertOrderButton.setVisible(true);
    }

    /**
     * displayBills
     * creates a PDF file for each bill, which takes into account all 3 tables from the project
     *
     * @param event
     */
    @FXML
    void displayBills(ActionEvent event) throws DocumentException, FileNotFoundException {
        int i = 1;
        for (Order order : orderBLL.findAll()) {
            System.out.println(order.getClientId());
            Client client = clientBLL.findById(order.getClientId());
            Product product = productBLL.findById(order.getProductId());
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Bill for Order id " + order.getId() + ".pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Client name: " + client.getName().toString() + "\n" + "Client email: " + client.getEmail().toString() + "\n" + "Client age: " + client.getAge() + "\n"
                    + "Order number: " + order.getId() + "\n" + "Product name: " + product.getName() + "\n" + "Quantity: " + order.getQuantity() + "\n" + "Total price:" + order.getQuantity() * product.getPrice(), font);
            document.add(paragraph);
            document.close();
            i++;
        }
    }

    /**
     * displayOrders
     * populates the Orders table in order to be able to view them in the GUI
     *
     * @param event
     */
    @FXML
    void displayOrders(ActionEvent event) {
        initializeAll();
        ordersTable.setVisible(true);
        ObservableList<Order> allOrders = FXCollections.observableArrayList(orderDAO.findAll());
        ordersTable.setItems(allOrders);
    }

    @FXML
    void selectClient1(ActionEvent event) {

    }

    @FXML
    void selectProduct1(ActionEvent event) {

    }

    @FXML
    void selectQuantity1(ActionEvent event) {

    }

    @FXML
    void showOrdersTable(ActionEvent event) {

    }

    /**
     * submitOrder
     * performs the update operation of an order into the DB
     *
     * @param event
     */
    @FXML
    void submitOrder(ActionEvent event) throws IllegalAccessException {
        if (isNumeric(selectQuantityT.getText())) {
            orderBLL.insert(new Order((Integer) selectClientCombo.getValue(), (Integer) selectProductCombo.getValue(), Integer.parseInt(selectQuantityT.getText())));
        } else
            JOptionPane.showMessageDialog(null, "The quantity must be an integer", "Error message", JOptionPane.WARNING_MESSAGE);
    }


}
