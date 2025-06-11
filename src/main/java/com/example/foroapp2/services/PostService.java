package com.example.foroapp2.services;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.repositories.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostService {

    private final PostRepository repository = new PostRepository();

    public List<Post> listarTodos() {
        return repository.listarTodos();
    }

    public List<Post> listarPorComunidad(long comunidadId) {
        return repository.listarTodos()
                         .stream()
                         .filter(p -> p.getComunidad() != null
                                   && p.getComunidad().getId() != null
                                   && p.getComunidad().getId() == comunidadId)
                         .collect(Collectors.toList());
    }

    public Optional<Post> buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public void crearPost(Post post) {
        repository.guardar(post);
    }

    public void actualizarPost(Post post) {
        repository.actualizar(post);
    }

    public boolean eliminarPost(long id) {
        return repository.eliminar(id);
    }

    /* ==== nueva funcionalidad de calificación ============================================ */

    public void calificar(long postId, boolean positivo) {
        buscarPorId(postId).ifPresent(post -> {
            if (positivo) {
                post.setLikes(post.getLikes() + 1);
            } else {
                post.setDislikes(post.getDislikes() + 1);
            }
            repository.actualizar(post);
        });
    }
}