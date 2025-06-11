package com.example.foroapp2;

import com.example.foroapp2.utils.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle("Foro App");
        stage.setScene(scene);


        SceneManager.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
