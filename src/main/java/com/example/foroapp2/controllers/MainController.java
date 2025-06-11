package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.models.Post;
import com.example.foroapp2.services.PostService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML
    private ListView<Comunidad> comunidadList;

    @FXML
    private ListView<Post> postList;

    @FXML
    private TextArea detallePost;

    @FXML
    private Button btnRefrescar;

    private ObservableList<Comunidad> comunidades = FXCollections.observableArrayList();
    private ObservableList<Post> posts = FXCollections.observableArrayList();
    private final PostService postService = new PostService();

    public void initialize() {
        comunidadList.setItems(comunidades);
        postList.setItems(posts);

        comunidadList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                posts.setAll(postService.listarPorComunidad(newVal.getId()));
            }
        });

        postList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                detallePost.setText(newVal.getContenido());
            }
        });

        btnRefrescar.setOnAction(e -> cargarComunidades());
        cargarComunidades();
    }

    private void cargarComunidades() {
        comunidades.clear();
        comunidades.addAll(
                new Comunidad(1L, "General"),
                new Comunidad(2L, "Tecnología"),
                new Comunidad(3L, "Deportes")
        );
    }
}
