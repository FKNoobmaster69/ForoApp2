package com.example.foroapp2.repositories;

import com.example.foroapp2.models.CalificacionUsuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CalificacionUsuarioRepository {
    private static final String RUTA_JSON = "data/calificaciones_usuarios.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, CalificacionUsuario> calificaciones = new HashMap<>();
    private long ultimoId = 0;

    public CalificacionUsuarioRepository() {
        cargar();
    }

    private void cargar() {
        try {
            File archivo = new File(RUTA_JSON);
            if (archivo.exists()) {
                List<CalificacionUsuario> lista = mapper.readValue(archivo, new TypeReference<List<CalificacionUsuario>>() {});
                for (CalificacionUsuario calificacion : lista) {
                    calificaciones.put(calificacion.getId(), calificacion);
                    if (calificacion.getId() > ultimoId) {
                        ultimoId = calificacion.getId();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar calificaciones", e);
        }
    }

    private void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RUTA_JSON), calificaciones.values());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar calificaciones", e);
        }
    }

    public List<CalificacionUsuario> listarTodos() {
        return new ArrayList<>(calificaciones.values());
    }

    public Optional<CalificacionUsuario> buscarPorId(long id) {
        return Optional.ofNullable(calificaciones.get(id));
    }

    public List<CalificacionUsuario> buscarPorUsuario(long usuarioId) {
        List<CalificacionUsuario> resultado = new ArrayList<>();
        for (CalificacionUsuario c : calificaciones.values()) {
            if (c.getUsuario().getId() == usuarioId) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void guardar(CalificacionUsuario calificacion) {
        if (calificacion.getId() == 0) {
            calificacion.setId(++ultimoId);
        }
        calificaciones.put(calificacion.getId(), calificacion);
        guardar();
    }

    public void actualizar(CalificacionUsuario calificacion) {
        calificaciones.put(calificacion.getId(), calificacion);
        guardar();
    }

    public void eliminar(long id) {
        calificaciones.remove(id);
        guardar();
    }
}
