package com.example.foroapp2.services;

import com.example.foroapp2.models.Tema;
import com.example.foroapp2.repositories.TemaRepository;

import java.util.List;
import java.util.Optional;

public class TemaService {
    private final TemaRepository repository = new TemaRepository();

    public List<Tema> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Tema> buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public List<Tema> listarPorComunidad(long comunidadId) {
        return repository.buscarPorComunidadId(comunidadId);
    }

    public void crearTema(Tema tema) {
        repository.guardar(tema);
    }

    public boolean actualizarTema(Tema tema) {
        return repository.buscarPorId(tema.getId())
                .map(t -> {
                    repository.actualizar(tema);
                    return true;
                }).orElse(false);
    }

    public boolean eliminarTema(long id) {
        return repository.buscarPorId(id)
                .map(t -> {
                    repository.eliminar(id);
                    return true;
                }).orElse(false);
    }
}
