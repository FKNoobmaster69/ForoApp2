package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Genero;
import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.utils.JsonFileUtils;
import com.example.foroapp2.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;
import java.util.*;

public class RegistroController {

    @FXML private TextField nombreField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML private RadioButton hombreRadio;
    @FXML private RadioButton mujerRadio;
    @FXML private RadioButton otroRadio;

    @FXML private ToggleGroup generoGroup;

    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private TextArea descripcionArea;

    @FXML private TextField facebookField;
    @FXML private TextField twitterField;
    @FXML private TextField instagramField;

    @FXML private Label mensajeLabel;

    @FXML
    public void initialize() {
        if (generoGroup == null) {
            generoGroup = new ToggleGroup();
            hombreRadio.setToggleGroup(generoGroup);
            mujerRadio.setToggleGroup(generoGroup);
            otroRadio.setToggleGroup(generoGroup);
        }
    }

    @FXML
    private void registrar() {
        String nombre = nombreField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            mensajeLabel.setText("Por favor, completa todos los campos obligatorios.");
            return;
        }

        if (!email.contains("@")) {
            mensajeLabel.setText("Correo electrónico inválido.");
            return;
        }

        RadioButton seleccionado = (RadioButton) generoGroup.getSelectedToggle();
        if (seleccionado == null) {
            mensajeLabel.setText("Selecciona un género.");
            return;
        }

        String generoTexto = seleccionado.getText().toUpperCase();
        Genero genero = switch (generoTexto) {
            case "HOMBRE" -> Genero.MASCULINO;
            case "MUJER" -> Genero.FEMENINO;
            default -> Genero.OTRO;
        };

        String descripcion = descripcionArea.getText().trim();
        String facebook = facebookField.getText().trim();
        String twitter = twitterField.getText().trim();
        String instagram = instagramField.getText().trim();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();

        String ruta = "src/main/resources/com/example/foroapp2/usuarios.json";
        List<Usuario> usuarios = JsonFileUtils.cargarListaDesdeArchivo(ruta, Usuario[].class).orElse(new ArrayList<>());

        boolean nombreExiste = usuarios.stream().anyMatch(u -> u.getNombre().equalsIgnoreCase(nombre));
        boolean emailExiste = usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));

        if (nombreExiste || emailExiste) {
            mensajeLabel.setText("El nombre de usuario o correo ya está en uso.");
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId(usuarios.size() + 1);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setContrasena(password);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setDescripcion(descripcion);
        nuevoUsuario.setFechaNacimiento(fechaNacimiento);
        nuevoUsuario.setGenero(genero);

        Map<String, String> redes = new HashMap<>();
        if (!facebook.isEmpty()) redes.put("facebook", facebook);
        if (!twitter.isEmpty()) redes.put("twitter", twitter);
        if (!instagram.isEmpty()) redes.put("instagram", instagram);
        nuevoUsuario.setRedesSociales(redes);
        nuevoUsuario.setComunidades(new ArrayList<>());

        usuarios.add(nuevoUsuario);
        JsonFileUtils.guardarEnArchivo(ruta, usuarios);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Usuario registrado correctamente. Serás redirigido al login.");
        alert.showAndWait();

        SceneManager.cambiarEscena("login");
    }

    @FXML
    private void volverLogin() {
        SceneManager.cambiarEscena("login");
    }
}
