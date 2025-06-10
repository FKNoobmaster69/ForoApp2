package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class UsuarioRepository {
    private static final String RUTA_JSON_CLASSPATH = "/data/usuarios.json";
    private static final String RUTA_JSON_FILE = "data/usuarios.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private long secuenciaId = 1;

    public UsuarioRepository() {
        cargar();
    }

    private void cargar() {
        try (InputStream is = getClass().getResourceAsStream(RUTA_JSON_CLASSPATH)) {
            if (is != null) {
                List<Usuario> lista = mapper.readValue(is, new TypeReference<List<Usuario>>() {});
                for (Usuario u : lista) {
                    usuarios.put(u.getId(), u);
                    if (u.getId() >= secuenciaId) secuenciaId = u.getId() + 1;
                }
            } else {
                Path path = Path.of(RUTA_JSON_FILE);
                if (Files.exists(path)) {
                    List<Usuario> lista = mapper.readValue(Files.newInputStream(path), new TypeReference<List<Usuario>>() {});
                    for (Usuario u : lista) {
                        usuarios.put(u.getId(), u);
                        if (u.getId() >= secuenciaId) secuenciaId = u.getId() + 1;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar usuarios", e);
        }
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    public Optional<Usuario> buscarPorNombre(String nombre) {
        return usuarios.values().stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public void guardar(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(secuenciaId++);
        }
        usuarios.put(usuario.getId(), usuario);
        persistir();
    }

    private void persistir() {
        try {
            Path path = Path.of(RUTA_JSON_FILE);
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), listarTodos());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar usuarios", e);
        }
    }
}