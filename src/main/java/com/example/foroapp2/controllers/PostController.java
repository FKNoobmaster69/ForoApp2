package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.services.PostService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PostController {

    @FXML
    private Label tituloLabel;

    @FXML
    private Label autorLabel;

    @FXML
    private TextArea contenidoArea;

    @FXML
    private Button likeButton;

    @FXML
    private Button dislikeButton;

    private Post post;
    private final PostService postService = new PostService();

    public void setPost(Post post) {
        this.post = post;
        mostrarDatos();
    }

    private void mostrarDatos() {
        if (post != null) {
            tituloLabel.setText(post.getTitulo());
            autorLabel.setText("Autor: " + post.getAutor());
            contenidoArea.setText(post.getContenido());
        }
    }

    @FXML
    private void onLike() {
        if (post != null) {
            postService.calificar(post.getId(), true);
            post.setLikes(post.getLikes() + 1);
        }
    }

    @FXML
    private void onDislike() {
        if (post != null) {
            postService.calificar(post.getId(), false);
            post.setDislikes(post.getDislikes() + 1);
        }
    }
}
