package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.utils.JsonFileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostRepository {

    private final String filePath = "data/posts.json";



    public List<Post> listarTodos() {
        return JsonFileUtils
                .cargarDesdeArchivo(filePath, Post[].class)
                .map(List::of)
                .orElse(new ArrayList<>());
    }

    public Optional<Post> buscarPorId(long id) {
        return listarTodos()
                .stream()
                .filter(p -> p.getId() != null && p.getId() == id)
                .findFirst();
    }

    public List<Post> buscarPorComunidadId(long comunidadId) {
        return listarTodos()
                .stream()
                .filter(p -> p.getComunidad() != null
                          && p.getComunidad().getId() != null
                          && p.getComunidad().getId() == comunidadId)
                .collect(Collectors.toList());
    }


    public void guardar(Post post) {
        synchronized (this) {
            List<Post> posts = listarTodos();
            post.setId(generarNuevoId(posts));
            posts.add(post);
            JsonFileUtils.guardarEnArchivo(filePath, posts);
        }
    }


    public void actualizar(Post post) {
        if (post.getId() == null) return;

        synchronized (this) {
            List<Post> posts = listarTodos();
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getId().equals(post.getId())) {
                    posts.set(i, post);
                    JsonFileUtils.guardarEnArchivo(filePath, posts);
                    break;
                }
            }
        }
    }

    public boolean eliminar(long id) {
        synchronized (this) {
            List<Post> posts = listarTodos()
                    .stream()
                    .filter(p -> p.getId() == null || p.getId() != id)
                    .collect(Collectors.toList());

            JsonFileUtils.guardarEnArchivo(filePath, posts);
            return true;
        }
    }


    private long generarNuevoId(List<Post> posts) {
        return posts.stream()
                    .mapToLong(p -> p.getId() != null ? p.getId() : 0)
                    .max()
                    .orElse(0) + 1;
    }
}