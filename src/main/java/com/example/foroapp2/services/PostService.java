package com.example.foroapp2.services;

import com.example.foroapp2.models.Post;
import com.example.foroapp2.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository repository = new PostRepository();

    public List<Post> listarTodos() {
        return repository.listarTodos();
    }

    public List<Post> listarPorComunidad(Long comunidadId) {
        return repository.listarPorComunidad(comunidadId);
    }

    public Optional<Post> buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public void guardar(Post post) {
        repository.guardar(post);
    }

    public void eliminar(long id) {
        repository.eliminar(id);
    }

    public void actualizar(Post post) {
        repository.actualizar(post);
    }

    public void calificar(long postId, boolean positivo) {
        Optional<Post> opt = buscarPorId(postId);
        if (opt.isPresent()) {
            Post p = opt.get();
            if (positivo) {
                p.setLikes(p.getLikes() + 1);
            } else {
                p.setDislikes(p.getDislikes() + 1);
            }
            actualizar(p);
        }
    }
}
