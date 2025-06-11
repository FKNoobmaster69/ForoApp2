package com.example.foroapp2.repositories;

import com.example.foroapp2.models.CalificacionUsuario;
import com.example.foroapp2.utils.JsonFileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalificacionUsuarioRepository {
    private final String filePath = "data/calificaciones_usuario.json";

    public List<CalificacionUsuario> listarTodos() {
        return JsonFileUtils.cargarDesdeArchivo(filePath, CalificacionUsuario[].class).map(List::of).orElse(new ArrayList<>());
    }

    public Optional<CalificacionUsuario> buscarPorId(long id) {
        return listarTodos().stream().filter(c -> c.getId() == id).findFirst();
    }

    public void guardar(CalificacionUsuario calificacion) {
        synchronized (this) {
            List<CalificacionUsuario> calificaciones = listarTodos();
            calificacion.setId(generarNuevoId(calificaciones));
            calificaciones.add(calificacion);
            JsonFileUtils.guardarEnArchivo(filePath, calificaciones);
        }
    }

    private long generarNuevoId(List<CalificacionUsuario> calificaciones) {
        return calificaciones.stream().mapToLong(CalificacionUsuario::getId).max().orElse(0) + 1;
    }
}
