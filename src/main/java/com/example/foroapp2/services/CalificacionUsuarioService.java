package com.example.foroapp2.services;

import com.example.foroapp2.models.CalificacionUsuario;

import java.util.ArrayList;
import java.util.List;

public class CalificacionUsuarioService {

    private List<CalificacionUsuario> calificaciones = new ArrayList<>();

    public void agregarCalificacion(CalificacionUsuario calificacion) {
        calificaciones.add(calificacion);
    }

    public List<CalificacionUsuario> listarTodos() {
        return new ArrayList<>(calificaciones);
    }
}
