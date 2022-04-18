package org.example.Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private Button showClientMenu;

    @FXML
    private Button showOrderMenu;

    @FXML
    private Button showProductMenu;

    /**
     * showClient
     * instantiate the Client Menu
     *
     * @param event
     */
    @FXML
    void showClient(ActionEvent event) {
        if (event.getSource() == showClientMenu) {
            try {
                //Start.stage.close();
                FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentThree\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_3\\src\\main\\java\\org\\example\\Presentation\\ClientMenu.fxml").toURI().toURL());
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * showClient
     * instantiate the Order Menu
     *
     * @param event
     */
    @FXML
    void showOrder(ActionEvent event) {
        if (event.getSource() == showOrderMenu) {
            try {
                //Start.stage.close();
                FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentThree\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_3\\src\\main\\java\\org\\example\\Presentation\\OrderMenu.fxml").toURI().toURL());
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * showClient
     * instantiate the Product Menu
     *
     * @param event
     */
    @FXML
    void showProduct(ActionEvent event) {
        if (event.getSource() == showProductMenu) {
            try {
                //Start.stage.close();
                FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentThree\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_3\\src\\main\\java\\org\\example\\Presentation\\ProductMenu.fxml").toURI().toURL());
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
