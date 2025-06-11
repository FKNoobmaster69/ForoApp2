package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.function.Consumer;

public class NuevoPostController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtContenido;

    @FXML
    private Button btnCrear;

    private Usuario usuarioActual;
    private Consumer<Post> postCreadoCallback;

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public void setPostCreadoCallback(Consumer<Post> callback) {
        this.postCreadoCallback = callback;
    }

    @FXML
    private void crearPost() {
        String titulo = txtTitulo.getText();
        String contenido = txtContenido.getText();

        if (titulo != null && !titulo.isBlank() && contenido != null && !contenido.isBlank()) {
            Post nuevoPost = new Post(titulo, contenido, usuarioActual.getNombre(), null);
            if (postCreadoCallback != null) {
                postCreadoCallback.accept(nuevoPost);
            }
        }
    }
}
