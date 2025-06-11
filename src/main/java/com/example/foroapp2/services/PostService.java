package com.example.foroapp2.services;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.models.Post;
import com.example.foroapp2.repositories.PostRepository;

import java.util.ArrayList;
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

    public List<Post> generarPostsRandom() {
        List<Post> posts = new ArrayList<>();
        Comunidad comunidadDefault = null;

        posts.add(new Post("Dato curioso 1", "El sol es una estrella de tipo espectral G2V.", "Admin", comunidadDefault));
        posts.add(new Post("Dato curioso 2", "El corazón humano late alrededor de 100,000 veces al día.", "Admin", comunidadDefault));
        posts.add(new Post("Dato curioso 3", "La Gran Muralla China mide más de 21,000 km de largo.", "Admin", comunidadDefault));
        posts.add(new Post("Dato curioso 4", "El récord mundial de salto con pértiga es 6.18 metros.", "Admin", comunidadDefault));
        posts.add(new Post("Dato curioso 5", "La luz tarda aproximadamente 8 minutos en llegar del Sol a la Tierra.", "Admin", comunidadDefault));

        return posts;
    }
}
