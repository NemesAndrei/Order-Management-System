package org.example.Start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;

/**
 * @author Nemes Mihnea-Andrei
 * application start point
 * @since 27 Apr,2021
 */

public class Start extends Application {
    public static Stage stage;
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * start
     * Starts the GUI stage and initiates the main screen of the application
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentThree\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_3\\src\\main\\java\\org\\example\\Presentation\\GUI.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Shop Simulator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main
     * launches the GUI
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        launch(args);
    }
}

