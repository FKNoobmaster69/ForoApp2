package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Tag;
import com.example.foroapp2.utils.JsonFileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagRepository {

    private final String filePath = "data/tags.json";

    public List<Tag> listarTodos() {
        return JsonFileUtils
                .cargarDesdeArchivo(filePath, Tag[].class)
                .map(List::of)
                .orElse(new ArrayList<>());
    }

    public Optional<Tag> buscarPorId(long id) {
        return listarTodos()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public void guardar(Tag tag) {
        synchronized (this) {
            List<Tag> tags = listarTodos();
            tag.setId(generarNuevoId(tags));
            tags.add(tag);
            JsonFileUtils.guardarEnArchivo(filePath, tags);
        }
    }

    private long generarNuevoId(List<Tag> tags) {
        return tags.stream()
                   .mapToLong(t -> t.getId() != null ? t.getId() : 0)
                   .max()
                   .orElse(0) + 1;
    }
}