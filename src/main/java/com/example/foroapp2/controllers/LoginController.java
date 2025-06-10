package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.UsuarioService;
import com.example.foroapp2.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void handleLogin(ActionEvent event) {
        String nombre = usernameField.getText().trim();
        String contrasena = passwordField.getText();
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorNombre(nombre);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContrasena().equals(contrasena)) {
                SceneManager.cambiarEscena("principal.fxml");
            } else {
                errorLabel.setText("Contraseña incorrecta");
            }
        } else {
            errorLabel.setText("Usuario no encontrado");
        }
    }

    @FXML
    private void handleRegistro(ActionEvent event) {
        SceneManager.cambiarEscena("registro.fxml");
    }
}

