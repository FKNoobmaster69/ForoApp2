package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.models.Post;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.PostService;
import com.example.foroapp2.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NuevoPostController {

    @FXML private TextField tituloField;
    @FXML private TextArea  contenidoArea;
    @FXML private Button    guardarButton;
    @FXML private Button    cancelarButton;

    private final PostService postService = new PostService();
    private Usuario    usuarioActual;
    private Comunidad  comunidadActual;

    public void setContext(Usuario usuario, Comunidad comunidad) {
        this.usuarioActual   = usuario;
        this.comunidadActual = comunidad;
    }

    @FXML
    private void guardarPost() {
        String titulo    = tituloField.getText().trim();
        String contenido = contenidoArea.getText().trim();
        if (!titulo.isEmpty() && !contenido.isEmpty()) {
            Post post = new Post();
            post.setTitulo(titulo);
            post.setContenido(contenido);
            post.setAutor(usuarioActual);
            post.setComunidad(comunidadActual);
            postService.crearPost(post);
            SceneManager.cambiarEscena("main-view.fxml");
        }
    }

    @FXML
    private void cancelar() {
        SceneManager.cambiarEscena("main-view.fxml");
    }
}