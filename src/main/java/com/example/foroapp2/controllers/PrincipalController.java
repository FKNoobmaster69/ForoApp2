package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.utils.JsonFileUtils;
import com.example.foroapp2.services.PostService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PrincipalController {

    @FXML
    private Label lblBienvenida;

    @FXML
    private ListView<Post> postList;

    @FXML
    private TextArea detallePost;

    @FXML
    private Button btnNuevoPost;

    private ObservableList<Post> posts = FXCollections.observableArrayList();
    private Usuario usuarioActual;
    private List<Usuario> usuarios;
    private final PostService postService = new PostService();

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void init() {
        lblBienvenida.setText("¡Bienvenido " + (usuarioActual != null ? usuarioActual.getNombre() : "Usuario") + "!");

        if (usuarioActual != null && usuarioActual.getPosts() != null) {
            posts.addAll(usuarioActual.getPosts());
        }

        List<Post> postsRandom = postService.generarPostsRandom();
        posts.addAll(postsRandom);

        postList.setItems(posts);
        postList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Post item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getTitulo() + " - " + item.getAutor());
            }
        });

        postList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                detallePost.setText(newVal.getContenido());
            }
        });

        btnNuevoPost.setOnAction(e -> abrirDialogoNuevoPost());
    }

    private void abrirDialogoNuevoPost() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foroapp2/nuevo-post.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Nuevo Post");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            NuevoPostController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);
            controller.setPostCreadoCallback(post -> {
                posts.add(post);
                usuarioActual.getPosts().add(post);
                if (usuarios != null) {
                    for (int i = 0; i < usuarios.size(); i++) {
                        if (usuarios.get(i).getNombre().equals(usuarioActual.getNombre())) {
                            usuarios.set(i, usuarioActual);
                            break;
                        }
                    }
                    JsonFileUtils.guardarEnArchivo("data/usuarios.json", usuarios);
                }
                stage.close();
            });

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
