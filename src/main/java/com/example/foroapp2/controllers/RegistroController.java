package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Genero;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.UsuarioService;
import com.example.foroapp2.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class RegistroController {

    @FXML private PasswordField passwordField;
    @FXML private TextField nombreField;
    @FXML private TextField emailField;
    @FXML private TextArea descripcionArea;
    @FXML private TextField redesField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private ComboBox<Genero> generoComboBox;
    @FXML private Label mensajeLabel;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        generoComboBox.getItems().setAll(Genero.values());
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        String nombre = nombreField.getText().trim();
        String password = passwordField.getText();
        String email = emailField.getText().trim();
        Genero genero = generoComboBox.getValue();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();

        if (nombre.isEmpty() || password.isEmpty() || email.isEmpty() || genero == null || fechaNacimiento == null) {
            mensajeLabel.setText("Completa todos los campos obligatorios.");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContrasena(password);
        usuario.setEmail(email);
        usuario.setDescripcion(descripcionArea.getText().trim());
        usuario.setRedesSociales(redesField.getText().trim());
        usuario.setGenero(genero);
        usuario.setFechaNacimiento(fechaNacimiento);

        usuarioService.registrar(usuario);
        SceneManager.cambiarEscena("login.fxml");
    }

    @FXML
    private void volverLogin(ActionEvent event) {
        SceneManager.cambiarEscena("login.fxml");
    }
}
