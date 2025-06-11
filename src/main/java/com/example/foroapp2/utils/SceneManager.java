package com.example.foroapp2.utils;


import com.example.foroapp2.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void cambiarEscena(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(fxml));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            
            String css = SceneManager.class
                    .getResource("/styles/estilos.css")
                    .toExternalForm();
            scene.getStylesheets().add(css);

            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cambiar de escena: " + e.getMessage());
        }
    }
}