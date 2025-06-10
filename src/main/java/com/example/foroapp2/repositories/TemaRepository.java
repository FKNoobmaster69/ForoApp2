package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Tema;
import com.example.foroapp2.utils.JsonFileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TemaRepository {
    private final String filePath = "data/temas.json";

    public List<Tema> listarTodos() {
        return JsonFileUtils.leerArchivo(filePath, Tema[].class);
    }

    public Optional<Tema> buscarPorId(long id) {
        return listarTodos().stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public List<Tema> buscarPorComunidadId(long comunidadId) {
        return listarTodos().stream()
                .filter(t -> t.getComunidad() != null && t.getComunidad().getId() == comunidadId)
                .collect(Collectors.toList());
    }

    public void guardar(Tema tema) {
        List<Tema> temas = listarTodos();
        tema.setId(generarNuevoId(temas));
        temas.add(tema);
        JsonFileUtils.guardarArchivo(filePath, temas);
    }

    public void actualizar(Tema temaActualizado) {
        List<Tema> temas = listarTodos();
        List<Tema> actualizados = new ArrayList<>();
        for (Tema tema : temas) {
            if (tema.getId() == temaActualizado.getId()) {
                actualizados.add(temaActualizado);
            } else {
                actualizados.add(tema);
            }
        }
        JsonFileUtils.guardarArchivo(filePath, actualizados);
    }

    public void eliminar(long id) {
        List<Tema> temas = listarTodos();
        temas.removeIf(t -> t.getId() == id);
        JsonFileUtils.guardarArchivo(filePath, temas);
    }

    private long generarNuevoId(List<Tema> temas) {
        return temas.stream().mapToLong(Tema::getId).max().orElse(0) + 1;
    }
}
