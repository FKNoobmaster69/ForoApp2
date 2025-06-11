package com.example.foroapp2.controllers;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.models.Post;
import com.example.foroapp2.services.ComunidadService;
import com.example.foroapp2.services.PostService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 * Controlador principal de la vista «main-view.fxml».
 * Muestra las comunidades, los posts y el detalle del post seleccionado.
 */
public class MainController {

    @FXML private ListView<Comunidad> comunidadList;
    @FXML private ListView<Post>      postList;
    @FXML private TextArea            detallePost;
    @FXML private Button              onRefresh;

    private final ComunidadService comunidadService = new ComunidadService();
    private final PostService      postService      = new PostService();

    /* ====== Ciclo de vida de la vista ======================================================= */

    @FXML
    public void initialize() {
        cargarDatosDePrueba();
        cargarComunidadesEnLista();

        /* Cuando cambia la comunidad seleccionada actualiza los posts mostrados */
        comunidadList.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                postList.getItems().setAll(postService.listarPorComunidad(newSel.getId()));
            } else {
                postList.getItems().clear();
            }
        });

        /* Cuando cambia el post seleccionado muestra su contenido */
        postList.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            detallePost.setText(newSel != null ? newSel.getContenido() : "");
        });

        /* Seleccionar la primera comunidad si existe */
        if (!comunidadList.getItems().isEmpty()) {
            comunidadList.getSelectionModel().selectFirst();
        }
    }

    /* ====== Acciones de UI ================================================================== */

    @FXML
    private void refrescar() {
        cargarComunidadesEnLista();
    }

    /* ====== Métodos auxiliares =============================================================== */

    /** Rellena la lista de comunidades desde el servicio. */
    private void cargarComunidadesEnLista() {
        comunidadList.getItems().setAll(comunidadService.listarTodos());
    }

    /**
     * Crea una comunidad y un post de ejemplo sólo si aún no existen datos.
     * Evita mostrar la aplicación vacía en el primer arranque.
     */
    private void cargarDatosDePrueba() {
        if (comunidadService.listarTodos().isEmpty()) {
            // Crear comunidad de prueba
            Comunidad comunidad = new Comunidad();
            comunidad.setNombre("Comunidad General");
            comunidad.setDescripcion("Bienvenido a la comunidad general.");
            comunidadService.crearComunidad(comunidad);

            // Crear post de prueba
            Post post = new Post();
            post.setTitulo("¡Bienvenido!");
            post.setContenido("Este es el primer post de la comunidad.");
            post.setComunidad(comunidad);
            postService.crearPost(post);
        }
    }
}