package com.example.foroapp2.services;

import com.example.foroapp2.models.CalificacionUsuario;
import com.example.foroapp2.repositories.CalificacionUsuarioRepository;

import java.util.List;
import java.util.Optional;

public class CalificacionUsuarioService {
    private final CalificacionUsuarioRepository repository = new CalificacionUsuarioRepository();

    public List<CalificacionUsuario> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<CalificacionUsuario> buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public List<CalificacionUsuario> listarPorUsuario(long usuarioId) {
        return repository.buscarPorUsuario(usuarioId);
    }

    public void calificarUsuario(CalificacionUsuario calificacion) {
        repository.guardar(calificacion);
    }

    public boolean actualizar(CalificacionUsuario calificacion) {
        if (repository.buscarPorId(calificacion.getId()).isPresent()) {
            repository.actualizar(calificacion);
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
