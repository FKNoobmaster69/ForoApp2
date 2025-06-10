package com.example.foroapp2.services;

import com.example.foroapp2.models.Tag;
import com.example.foroapp2.repositories.TagRepository;

import java.util.List;
import java.util.Optional;

public class TagService {
    private final TagRepository repository = new TagRepository();

    public List<Tag> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Tag> buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public Optional<Tag> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    public void guardar(Tag tag) {
        repository.guardar(tag);
    }

    public boolean actualizar(Tag tag) {
        if (repository.buscarPorId(tag.getId()).isPresent()) {
            repository.actualizar(tag);
            return true;
        }
        return false;
    }

    public boolean eliminar(long id) {
        if (repository.buscarPorId(id).isPresent()) {
            repository.eliminar(id);
            return true;
        }
        return false;
    }
}
