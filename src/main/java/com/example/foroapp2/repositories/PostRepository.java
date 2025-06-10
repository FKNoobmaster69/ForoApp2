package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String RUTA_JSON = "data/posts.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, Post> posts = new HashMap<>();
    private long ultimoId = 0;

    public PostRepository() {
        cargar();
    }

    private void cargar() {
        try {
            File file = new File(RUTA_JSON);
            if (file.exists()) {
                List<Post> lista = mapper.readValue(file, new TypeReference<List<Post>>() {});
                for (Post p : lista) {
                    posts.put(p.getId(), p);
                    if (p.getId() > ultimoId) ultimoId = p.getId();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar posts", e);
        }
    }

    private void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RUTA_JSON), posts.values());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar posts", e);
        }
    }

    public List<Post> listarTodos() {
        return new ArrayList<>(posts.values());
    }

    public List<Post> listarPorComunidad(long comunidadId) {
        return posts.values().stream()
                .filter(post -> post.getComunidad() != null && post.getComunidad().getId() == comunidadId)
                .collect(Collectors.toList());
    }

    public void guardar(Post post) {
        if (post.getId() == null || post.getId() == 0) {
            post.setId(++ultimoId);
        }
        posts.put(post.getId(), post);
        guardar();
    }

    public Optional<Post> buscarPorId(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public void eliminar(long id) {
        posts.remove(id);
        guardar();
    }

    public void actualizar(Post post) {
        posts.put(post.getId(), post);
        guardar();
    }
}
