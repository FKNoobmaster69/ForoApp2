package com.example.foroapp2.repositories;


import com.example.foroapp2.models.Comunidad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ComunidadRepository {
    private static final String RUTA_JSON = "data/comunidades.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, Comunidad> comunidades = new HashMap<>();
    private long ultimoId = 0;

    public ComunidadRepository() {
        cargar();
    }

    private void cargar() {
        try {
            File file = new File(RUTA_JSON);
            if (file.exists()) {
                List<Comunidad> lista = mapper.readValue(file, new TypeReference<List<Comunidad>>() {});
                for (Comunidad c : lista) {
                    comunidades.put(c.getId(), c);
                    if (c.getId() > ultimoId) ultimoId = c.getId();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar comunidades", e);
        }
    }

    private void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RUTA_JSON), comunidades.values());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar comunidades", e);
        }
    }

    public List<Comunidad> listarTodos() {
        return new ArrayList<>(comunidades.values());
    }

    public Optional<Comunidad> buscarPorId(long id) {
        return Optional.ofNullable(comunidades.get(id));
    }

    public void guardar(Comunidad comunidad) {
        if (comunidad.getId() == 0) {
            comunidad.setId(++ultimoId);
        }
        comunidades.put(comunidad.getId(), comunidad);
        guardar();
    }

    public void eliminar(long id) {
        comunidades.remove(id);
        guardar();
    }

    public void actualizar(Comunidad comunidad) {
        comunidades.put(comunidad.getId(), comunidad);
        guardar();
    }
}
