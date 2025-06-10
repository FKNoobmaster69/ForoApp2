package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Tag;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TagRepository {
    private static final String RUTA_JSON = "data/tags.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, Tag> tags = new HashMap<>();
    private long ultimoId = 0;

    public TagRepository() {
        cargar();
    }

    private void cargar() {
        try {
            File archivo = new File(RUTA_JSON);
            if (archivo.exists()) {
                List<Tag> lista = mapper.readValue(archivo, new TypeReference<List<Tag>>() {
                });
                for (Tag tag : lista) {
                    tags.put(tag.getId(), tag);
                    if (tag.getId() > ultimoId) ultimoId = tag.getId();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los tags", e);
        }
    }

    private void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RUTA_JSON), tags.values());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los tags", e);
        }
    }

    public List<Tag> listarTodos() {
        return new ArrayList<>(tags.values());
    }

    public Optional<Tag> buscarPorId(long id) {
        return Optional.ofNullable(tags.get(id));
    }

    public Optional<Tag> buscarPorNombre(String nombre) {
        return tags.values().stream()
                .filter(t -> t.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public void guardar(Tag tag) {
        if (tag.getId() == 0) {
            tag.setId(++ultimoId);
        }
        tags.put(tag.getId(), tag);
        guardar();
    }

    public void actualizar(Tag tag) {
        tags.put(tag.getId(), tag);
        guardar();
    }

    public void eliminar(long id) {
        tags.remove(id);
        guardar();
    }
}
