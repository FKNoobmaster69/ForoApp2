package com.example.foroapp2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button button;

    @FXML
    protected void onButtonClick() {
        new Alert(Alert.AlertType.INFORMATION, "¡Botón pulsado!").showAndWait();
    }
}
