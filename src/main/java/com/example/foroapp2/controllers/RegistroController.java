package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Genero;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.services.UsuarioService;
import com.example.foroapp2.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.util.HashMap;
import java.util.Map;

public class RegistroController {

    @FXML private TextField nombreField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextArea descripcionArea;
    @FXML private TextField facebookField;
    @FXML private TextField twitterField;
    @FXML private TextField instagramField;
    @FXML private RadioButton hombreRadio;
    @FXML private RadioButton mujerRadio;
    @FXML private RadioButton otroRadio;
    @FXML private ToggleGroup generoGroup;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void registrar() {
        String nombre = nombreField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        Genero generoSel = obtenerGeneroSeleccionado();
        if (nombre.isBlank() || email.isBlank() || password.isBlank()) return;

        Usuario usuario = new Usuario(nombre, email, password, generoSel);
        usuario.setDescripcion(descripcionArea.getText());

        Map<String, String> redes = new HashMap<>();
        if (!facebookField.getText().isBlank()) redes.put("facebook", facebookField.getText().trim());
        if (!twitterField.getText().isBlank()) redes.put("twitter", twitterField.getText().trim());
        if (!instagramField.getText().isBlank()) redes.put("instagram", instagramField.getText().trim());
        usuario.setRedesSociales(redes);

        usuarioService.registrar(usuario);
        limpiarFormulario();
    }

    @FXML
    private void volverLogin(ActionEvent event) {
        SceneManager.cambiarEscena("/com/example/foroapp2/login.fxml");
    }

    private Genero obtenerGeneroSeleccionado() {
        if (hombreRadio.isSelected()) return Genero.MASCULINO;
        if (mujerRadio.isSelected()) return Genero.FEMENINO;
        return Genero.NO_ESPECIFICADO;
    }

    private void limpiarFormulario() {
        nombreField.clear();
        emailField.clear();
        passwordField.clear();
        descripcionArea.clear();
        facebookField.clear();
        twitterField.clear();
        instagramField.clear();
        generoGroup.selectToggle(null);
    }
}