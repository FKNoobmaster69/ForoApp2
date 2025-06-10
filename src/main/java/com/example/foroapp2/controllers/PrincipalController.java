package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.PostService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PrincipalController {

    @FXML private VBox postContainer;
    @FXML private Button inicioButton, comunidadesButton, notificacionesButton, perfilButton, cerrarSesionButton;
    @FXML private ScrollPane scrollPane;

    private final PostService postService = new PostService();
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        cargarContenido();
    }

    private void cargarContenido() {
        List<Post> posts = postService.listarTodos();
        postContainer.getChildren().clear();
        for (Post post : posts) {
            Node postNode = crearPostCard(post);
            postContainer.getChildren().add(postNode);
        }
    }

    private Node crearPostCard(Post post) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 3);");

        HBox header = new HBox(10);
        ImageView avatar = new ImageView();
        avatar.setFitHeight(40);
        avatar.setFitWidth(40);

        VBox userInfo = new VBox(
                new Label(post.getAutor().getNombre()),
                new Label("Hace 2 horas")
        );
        header.getChildren().addAll(avatar, userInfo);

        Label contenido = new Label(post.getContenido());
        contenido.setWrapText(true);
        contenido.setStyle("-fx-font-size: 16px;");

        FlowPane tags = new FlowPane(5, 5);
        post.getTags().forEach(tag -> {
            Label tagLabel = new Label("#" + tag.getNombre());
            tagLabel.setStyle("-fx-text-fill: #0077cc;");
            tags.getChildren().add(tagLabel);
        });

        HBox botones = new HBox(15,
                new Button("👍"),
                new Button("👎"),
                new Button("💬 Comentarios")
        );

        card.getChildren().addAll(header, contenido, tags, botones);
        return card;
    }
}
