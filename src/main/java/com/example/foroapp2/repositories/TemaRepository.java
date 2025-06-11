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
        return JsonFileUtils
                .cargarDesdeArchivo(filePath, Tema[].class)
                .map(List::of)
                .orElse(new ArrayList<>());
    }

    public Optional<Tema> buscarPorId(Long id) {
        return listarTodos()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public List<Tema> buscarPorComunidadId(Long comunidadId) {
        return listarTodos()
                .stream()
                .filter(t -> t.getComunidad() != null
                          && t.getComunidad().getId().equals(comunidadId))
                .collect(Collectors.toList());
    }



    public void guardar(Tema tema) {
        synchronized (this) {
            List<Tema> temas = listarTodos();
            tema.setId(generarNuevoId(temas));
            temas.add(tema);
            JsonFileUtils.guardarEnArchivo(filePath, temas);
        }
    }

    public void actualizar(Tema temaActualizado) {
        List<Tema> actualizados = listarTodos()
                .stream()
                .map(t -> t.getId() == temaActualizado.getId() ? temaActualizado : t)
                .collect(Collectors.toList());

        JsonFileUtils.guardarEnArchivo(filePath, actualizados);
    }

    public void eliminar(Long id) {
        List<Tema> temas = listarTodos()
                .stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());

        JsonFileUtils.guardarEnArchivo(filePath, temas);
    }


    private long generarNuevoId(List<Tema> temas) {
        return temas.stream()
                    .mapToLong(Tema::getId)
                    .max()
                    .orElse(0L) + 1;
    }
}