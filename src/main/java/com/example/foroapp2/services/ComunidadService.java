package com.example.foroapp2.services;

import com.example.foroapp2.models.Comunidad;
import com.example.foroapp2.repositories.ComunidadRepository;

import java.util.List;
import java.util.Optional;

public class ComunidadService {
    private final ComunidadRepository repository = new ComunidadRepository();

    public List<Comunidad> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Comunidad> buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public void crearComunidad(Comunidad comunidad) {
        repository.guardar(comunidad);
    }

    public boolean actualizarComunidad(Comunidad comunidad) {
        if (repository.buscarPorId(comunidad.getId()).isPresent()) {
            repository.actualizar(comunidad);
            return true;
        }
        return false;
    }

    public boolean eliminarComunidad(long id) {
        if (repository.buscarPorId(id).isPresent()) {
            repository.eliminar(id);
            return true;
        }
        return false;
    }
}
