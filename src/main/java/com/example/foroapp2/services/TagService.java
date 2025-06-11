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

    public void crear(Tag tag) {
        repository.guardar(tag);
    }
}