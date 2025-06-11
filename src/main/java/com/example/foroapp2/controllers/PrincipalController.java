package com.example.foroapp2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrincipalController {

    @FXML
    private Label lblBienvenida;

    public void initialize() {
        lblBienvenida.setText("¡Bienvenido al foro!");
    }
}
