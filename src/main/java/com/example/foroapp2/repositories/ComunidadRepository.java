package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.utils.JsonFileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComunidadRepository {

    private final String filePath = "data/comunidades.json";

    public List<Comunidad> listarTodos() {
        return JsonFileUtils
                .cargarDesdeArchivo(filePath, Comunidad[].class)
                .map(List::of)
                .orElse(new ArrayList<>());
    }

    public Optional<Comunidad> buscarPorId(long id) {
        return listarTodos()
                .stream()
                .filter(c -> c.getId() != null && c.getId() == id)
                .findFirst();
    }

    public Optional<Comunidad> buscarPorNombre(String nombre) {
        return listarTodos()
                .stream()
                .filter(c -> c.getNombre() != null && c.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public void guardar(Comunidad comunidad) {
        synchronized (this) {
            List<Comunidad> comunidades = listarTodos();
            comunidad.setId(generarNuevoId(comunidades));
            comunidades.add(comunidad);
            JsonFileUtils.guardarEnArchivo(filePath, comunidades);
        }
    }

    public void actualizar(Comunidad comunidadActualizada) {
        List<Comunidad> comunidades = listarTodos()
                .stream()
                .map(c -> c.getId().equals(comunidadActualizada.getId()) ? comunidadActualizada : c)
                .collect(Collectors.toList());

        JsonFileUtils.guardarEnArchivo(filePath, comunidades);
    }

    public boolean eliminar(long id) {
        List<Comunidad> comunidades = listarTodos()
                .stream()
                .filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());

        JsonFileUtils.guardarEnArchivo(filePath, comunidades);
        return true;
    }

    private long generarNuevoId(List<Comunidad> comunidades) {
        return comunidades.stream()
                          .mapToLong(c -> c.getId() == null ? 0L : c.getId())
                          .max()
                          .orElse(0L) + 1;
    }
}