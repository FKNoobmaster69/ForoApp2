package com.example.foroapp2.services;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.repositories.ComunidadRepository;

import java.util.List;
import java.util.Optional;

public class ComunidadService {

    private final ComunidadRepository repository = new ComunidadRepository();

    public List<Comunidad> listarTodas() {
        return repository.listarTodos();
    }

    public List<Comunidad> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Comunidad> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Optional<Comunidad> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    public void crearComunidad(Comunidad comunidad) {
        repository.guardar(comunidad);
    }

    public void actualizarComunidad(Comunidad comunidad) {
        repository.actualizar(comunidad);
    }

    public boolean eliminarComunidad(Long id) {
        return repository.eliminar(id);
    }
}