package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.models.Genero;
import com.example.foroapp2.models.Post;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.ComunidadService;
import com.example.foroapp2.services.PostService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML private ListView<Comunidad> comunidadList;
    @FXML private ListView<Post> postList;
    @FXML private TextArea detallePost;
    @FXML private Button onRefresh;

    private final ComunidadService comunidadService = new ComunidadService();
    private final PostService postService = new PostService();

    @FXML
    public void initialize() {
        cargarDatosDePrueba();
        comunidadList.getItems().setAll(comunidadService.listarTodos());

        comunidadList.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                postList.getItems().setAll(postService.listarPorComunidad(sel.getId()));
            } else {
                postList.getItems().clear();
            }
        });

        postList.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            detallePost.setText(sel != null ? sel.getContenido() : "");
        });

        if (!comunidadList.getItems().isEmpty()) {
            comunidadList.getSelectionModel().selectFirst();
        }
    }

    private void cargarDatosDePrueba() {
        if (comunidadService.listarTodos().isEmpty()) {
            Usuario demo = new Usuario("Admin", "admin@foro.com", "1234", Genero.MASCULINO);

            Comunidad c1 = new Comunidad("Java", "Todo sobre Java", "", false, demo);
            Comunidad c2 = new Comunidad("Diseño", "UX/UI", "", false, demo);
            Comunidad c3 = new Comunidad("Bases de Datos", "SQL y NoSQL", "", false, demo);

            comunidadService.crearComunidad(c1);
            comunidadService.crearComunidad(c2);
            comunidadService.crearComunidad(c3);

            Post p1 = new Post("¿Cómo usar lambdas?", "Las lambdas son importantes desde Java 8", demo, c1);
            Post p2 = new Post("¿Qué es Material Design?", "Guías de interfaz por Google", demo, c2);
            Post p3 = new Post("SQL vs NoSQL", "¿Cuál conviene más?", demo, c3);

            postService.guardar(p1);
            postService.guardar(p2);
            postService.guardar(p3);
        }
    }

    @FXML
    protected void onRefresh() {
        comunidadList.getItems().setAll(comunidadService.listarTodos());
        postList.getItems().clear();
        detallePost.clear();

        if (!comunidadList.getItems().isEmpty()) {
            comunidadList.getSelectionModel().selectFirst();
        }
    }
}
