package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.UsuarioService;
import com.example.foroapp2.utils.SceneManager;
import com.example.foroapp2.utils.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label mensajeErrorLabel;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        // Inicializar campos y limpiar mensajes de error
        mensajeErrorLabel.setText("");
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            mensajeErrorLabel.setText("Por favor, completa todos los campos");
            return;
        }

        Optional<Usuario> usuarioOptional = usuarioService.validarCredenciales(email, password);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            SessionManager.iniciarSesion(usuario);
            SceneManager.cambiarEscena("principal.fxml");
        } else {
            mensajeErrorLabel.setText("Email o contraseña incorrectos");
        }
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        SceneManager.cambiarEscena("registro.fxml");
    }
}
