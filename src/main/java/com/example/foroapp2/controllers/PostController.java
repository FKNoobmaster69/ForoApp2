package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.services.PostService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PostController {

    @FXML private Label tituloLabel;
    @FXML private Label autorLabel;
    @FXML private TextArea contenidoArea;
    @FXML private Button likeButton;
    @FXML private Button dislikeButton;

    private final PostService postService = new PostService();
    private Post post;

    public void setPost(Post post) {
        this.post = post;
        tituloLabel.setText(post.getTitulo());
        autorLabel.setText(post.getAutor().getNombre());
        contenidoArea.setText(post.getContenido());
    }

    @FXML
    private void like() {
        postService.calificar(post.getId(), true);
        likeButton.setDisable(true);
        dislikeButton.setDisable(false);
    }

    @FXML
    private void dislike() {
        postService.calificar(post.getId(), false);
        dislikeButton.setDisable(true);
        likeButton.setDisable(false);
    }
}
