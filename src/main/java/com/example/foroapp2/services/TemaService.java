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

    public Optional<Tema> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public List<Tema> buscarPorComunidadId(Long comunidadId) {
        return repository.buscarPorComunidadId(comunidadId);
    }

    public void crearTema(Tema tema) {
        repository.guardar(tema);
    }

    public void actualizarTema(Tema tema) {
        repository.actualizar(tema);
    }

    public void eliminarTema(Long id) {
        repository.eliminar(id);
    }
}