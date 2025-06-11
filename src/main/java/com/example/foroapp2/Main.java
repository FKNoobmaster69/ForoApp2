package com.example.foroapp2;

import com.example.foroapp2.utils.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        SceneManager.setStage(primaryStage);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foroapp2/login.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        primaryStage.setTitle("Foro App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
