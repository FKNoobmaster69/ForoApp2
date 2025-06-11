package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Tema;
import com.example.foroapp2.services.TemaService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TemaController {

    @FXML private ListView<Tema> temasListView;
    @FXML private TextField      nuevoTemaField;

    private final TemaService temaService = new TemaService();

    @FXML
    public void initialize() {
        temasListView.getItems().setAll(temaService.listarTodos());
    }

    @FXML
    private void crearTema() {
        String nombre = nuevoTemaField.getText().trim();
        if (!nombre.isEmpty()) {
            Tema t = new Tema();
            t.setNombre(nombre);
            t.setDescripcion("");
            temaService.crearTema(t);
            temasListView.getItems().setAll(temaService.listarTodos());
            nuevoTemaField.clear();
        }
    }
}