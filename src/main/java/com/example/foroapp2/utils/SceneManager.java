package com.example.foroapp2.utils;

import com.example.foroapp2.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void cambiarEscena(String fxmlName, String titulo) {
        try {
            String fxmlPath = "/com/example/foroapp2/" + fxmlName + ".fxml";
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String cssPath = "/styles/estilos.css";
            scene.getStylesheets().add(MainApplication.class.getResource(cssPath).toExternalForm());
            mainStage.setScene(scene);
            if (titulo != null) {
                mainStage.setTitle(titulo);
            }
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambiarEscena(String fxmlName) {
        cambiarEscena(fxmlName, null);
    }
}
