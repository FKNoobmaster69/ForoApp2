package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.services.ComunidadService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ComunidadController {

    @FXML private ListView<Comunidad> comunidadesListView;
    @FXML private TextField           nuevaComunidadField;

    private final ComunidadService comunidadService = new ComunidadService();

    @FXML
    public void initialize() {
        comunidadesListView.getItems().setAll(comunidadService.listarTodas());
    }

    @FXML
    private void crearComunidad() {
        String nombre = nuevaComunidadField.getText().trim();
        if (!nombre.isEmpty()) {
            Comunidad c = new Comunidad();
            c.setNombre(nombre);
            comunidadService.crearComunidad(c);
            comunidadesListView.getItems().setAll(comunidadService.listarTodas());
            nuevaComunidadField.clear();
        }
    }
}