package com.example.foroapp2.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

public class TemaController {

    @FXML
    private ListView<String> temasListView;

    @FXML
    private TextField nuevoTemaField;

    private final ObservableList<String> temas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        temas.addAll("General", "Anuncios", "Ayuda");
        temasListView.setItems(temas);
    }

    @FXML
    private void crearTema() {
        String tema = nuevoTemaField.getText().trim();
        if (tema.isEmpty()) {
            mostrarAlerta("Error", "El nombre del tema no puede estar vacío.");
            return;
        }
        if (temas.contains(tema)) {
            mostrarAlerta("Error", "El tema ya existe.");
            return;
        }
        temas.add(tema);
        nuevoTemaField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
