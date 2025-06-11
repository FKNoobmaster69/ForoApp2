package com.example.foroapp2.services;

import com.example.foroapp2.models.TipoCalificacion;
import com.example.foroapp2.repositories.TipoCalificacionRepository;

import java.util.List;
import java.util.Optional;

public class TipoCalificacionService {

    private final TipoCalificacionRepository repository = new TipoCalificacionRepository();

    public List<TipoCalificacion> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoCalificacion> buscarPorId(long puntuacion) {
        return repository.buscarPorId(puntuacion);
    }

    public Optional<TipoCalificacion> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}
