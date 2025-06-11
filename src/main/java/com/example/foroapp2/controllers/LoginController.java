package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.utils.JsonFileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    private List<Usuario> usuarios = new ArrayList<>();

    @FXML
    public void initialize() {
        JsonFileUtils.cargarListaDesdeArchivo("data/usuarios.json", Usuario[].class)
                .ifPresentOrElse(
                        lista -> usuarios = lista,
                        () -> usuarios = new ArrayList<>()
                );
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
        String nombre = txtUsuario.getText().trim();
        String clave = txtPassword.getText().trim();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre) && usuario.getContrasena().equals(clave)) {
                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foroapp2/principal.fxml"));
                Parent root = loader.load();

                PrincipalController controller = loader.getController();
                controller.setUsuarioActual(usuario);
                controller.setUsuarios(usuarios);
                controller.init();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("ForoApp - Principal");
                stage.setMaximized(true);
                stage.show();
                return;
            }
        }

        txtUsuario.setStyle("-fx-border-color: red;");
        txtPassword.setStyle("-fx-border-color: red;");
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foroapp2/registro.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("ForoApp - Registrar nuevo usuario");
        stage.setResizable(false);
        stage.show();
    }
}
