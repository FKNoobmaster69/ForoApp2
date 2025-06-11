package com.example.foroapp2.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class ComunidadController {

    @FXML
    private ListView<String> comunidadList;

    @FXML
    private TextField nuevoComunidadField;

    private final ObservableList<String> comunidades = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        comunidades.addAll("Java", "Programación", "Tecnología");
        comunidadList.setItems(comunidades);
    }

    @FXML
    private void crearComunidad() {
        String nombre = nuevoComunidadField.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "El nombre de la comunidad no puede estar vacío.");
            return;
        }
        if (comunidades.contains(nombre)) {
            mostrarAlerta("Error", "La comunidad ya existe.");
            return;
        }
        comunidades.add(nombre);
        nuevoComunidadField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
