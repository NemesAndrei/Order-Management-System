package org.example.Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Bll.ClientBLL;
import org.example.Dao.ClientDAO;
import org.example.Model.Client;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 * Client GUI Controller
 * implements the GUI for the Client operations
 * @since 27 Apr, 2021
 */

public class ClientController implements Initializable {

    ClientDAO clientDao = new ClientDAO();
    Client client = new Client();
    ClientBLL clientBLL = new ClientBLL();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * initializeAll
     * clears the screen of the Client GUI
     */
    public void initializeAll() {
        ClientsTable.getColumns().clear();
        ObservableList<TableColumn<Client, ?>> tableColumns = clientDao.getColumnsHead(client);
        ClientsTable.getColumns().addAll(tableColumns);
        ClientsTable.setVisible(false);
        clientInsertLabel.setVisible(false);
        clientNameLabel.setVisible(false);
        clientEmailLabel.setVisible(false);
        clientAgeLabel.setVisible(false);
        clientNameText.setVisible(false);
        clientEmailText.setVisible(false);
        clientAgeText.setVisible(false);
        insertClientButton.setVisible(false);
        updateClientLabel.setVisible(false);
        updateClientCombo.setVisible(false);
        updateClientCombo.getItems().clear();
        ChooseClientUpdateB.setVisible(false);
        clientNameUpdate.setVisible(false);
        clientEmailUpdate.setVisible(false);
        clientAgeUpdate.setVisible(false);
        clientNameUpdateT.setVisible(false);
        clientEmailUpdateT.setVisible(false);
        clientAgeUpdateT.setVisible(false);
        updateClientB.setVisible(false);
        deleteClientCombo.setVisible(false);
        deleteClientCombo.getItems().clear();
        deleteClientB.setVisible(false);
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

    private void showMessageERR(String s) {
        JOptionPane.showMessageDialog(null, s, "Error message", JOptionPane.WARNING_MESSAGE);
    }

    private void showMessageCONF(String s) {
        JOptionPane.showMessageDialog(null, s, "Error message", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private Button viewClients;

    @FXML
    private Button addClient;

    @FXML
    private Button editClient;

    @FXML
    private Button deleteClient;

    @FXML
    private TableView ClientsTable;

    @FXML
    private Label clientInsertLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label clientEmailLabel;

    @FXML
    private Label clientAgeLabel;

    @FXML
    private TextField clientNameText;

    @FXML
    private TextField clientEmailText;

    @FXML
    private TextField clientAgeText;

    @FXML
    private Button insertClientButton;

    @FXML
    private ComboBox updateClientCombo;

    @FXML
    private Button ChooseClientUpdateB;

    @FXML
    private Label clientNameUpdate;

    @FXML
    private Label clientEmailUpdate;

    @FXML
    private Label clientAgeUpdate;

    @FXML
    private TextField clientNameUpdateT;

    @FXML
    private TextField clientEmailUpdateT;

    @FXML
    private TextField clientAgeUpdateT;

    @FXML
    private Button updateClientB;

    @FXML
    private ComboBox deleteClientCombo;

    @FXML
    private Button deleteClientB;

    @FXML
    private Label updateClientLabel;


    @FXML
    void chooseClient(ActionEvent event) {

    }

    /**
     * chooseClient1
     * initializes the elements needed to update a client
     *
     * @param event
     */
    @FXML
    void chooseClient1(ActionEvent event) {
        Client tempClient = clientBLL.findById((Integer) updateClientCombo.getValue());
        clientNameUpdateT.setText(tempClient.getName());
        clientEmailUpdateT.setText(tempClient.getEmail());
        clientAgeUpdateT.setText(String.valueOf(tempClient.getAge()));
        clientNameUpdate.setVisible(true);
        clientEmailUpdate.setVisible(true);
        clientAgeUpdate.setVisible(true);
        clientNameUpdateT.setVisible(true);
        clientEmailUpdateT.setVisible(true);
        clientAgeUpdateT.setVisible(true);
        updateClientB.setVisible(true);
    }

    /**
     * deleteClient
     * initializes the elements needed to delete a client
     *
     * @param event
     */
    @FXML
    void deleteClient(ActionEvent event) {
        initializeAll();
        updateClientLabel.setVisible(true);
        deleteClientB.setVisible(true);
        deleteClientCombo.setVisible(true);
        List<Client> clientList = clientBLL.findAll();
        for (Client client : clientList) {
            deleteClientCombo.getItems().add(client.getId());
        }
    }

    @FXML
    void deleteClient1(ActionEvent event) {
        clientBLL.delete((Integer) deleteClientCombo.getValue());
    }

    @FXML
    void deleteClient2(ActionEvent event) {

    }

    /**
     * insertClient
     * initializes the elements needed to insert a client
     *
     * @param event
     */
    @FXML
    void insertClient(ActionEvent event) {
        initializeAll();
        clientInsertLabel.setVisible(true);
        clientNameLabel.setVisible(true);
        clientEmailLabel.setVisible(true);
        clientAgeLabel.setVisible(true);
        clientNameText.setVisible(true);
        clientEmailText.setVisible(true);
        clientAgeText.setVisible(true);
        insertClientButton.setVisible(true);
    }

    /**
     * insertClientDB
     * performs the insert operation of a client into the DB
     *
     * @param event
     */
    @FXML
    void insertClientDB(ActionEvent event) throws IllegalAccessException {
        if (isNumeric(clientAgeText.getText())) {
            if (!clientBLL.insert(new Client(clientNameText.getText(), clientEmailText.getText(), Integer.parseInt(clientAgeText.getText())))) {
                showMessageERR("Invalid input data for client to be inserted into DB");
            } else {
                showMessageCONF("Client was inserted successfully");
                clientNameText.setText("");
                clientEmailText.setText("");
                clientAgeText.setText("");
            }
        } else
            JOptionPane.showMessageDialog(null, "The age must be an integer", "Error message", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * listAllClients
     * populates the Clients table in order to be able to view them in the GUI
     *
     * @param event
     */
    @FXML
    void listAllClients(ActionEvent event) {
        initializeAll();
        ClientsTable.setVisible(true);
        ObservableList<Client> allClients = FXCollections.observableArrayList(clientDao.findAll());
        ClientsTable.setItems(allClients);
    }

    @FXML
    void showClientsTable(ActionEvent event) {

    }

    /**
     * updateClient
     * initializes the elements needed to update a client
     *
     * @param event
     */
    @FXML
    void updateClient(ActionEvent event) {
        initializeAll();
        updateClientLabel.setVisible(true);
        updateClientCombo.setVisible(true);
        ChooseClientUpdateB.setVisible(true);
        List<Client> clientList = clientBLL.findAll();
        for (Client client : clientList) {
            updateClientCombo.getItems().add(client.getId());
        }
    }

    /**
     * updateClient1
     * performs the update operation of a client into the DB
     *
     * @param event
     */
    @FXML
    void updateClient1(ActionEvent event) throws IllegalAccessException {
        if (isNumeric(clientAgeUpdateT.getText())) {
            clientBLL.update((Integer) updateClientCombo.getValue(), new Client(clientNameUpdateT.getText(), clientEmailUpdateT.getText(), Integer.parseInt(clientAgeUpdateT.getText())));
        } else
            JOptionPane.showMessageDialog(null, "The age must be an integer", "Error message", JOptionPane.WARNING_MESSAGE);
    }


}
