package com.example.foroapp2.services;

import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioRepository repo = new UsuarioRepository();

    public List<Usuario> listarTodos() {
        return repo.listarTodos();
    }

    public Optional<Usuario> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }

    public void registrar(Usuario usuario) {
        repo.guardar(usuario);
    }

    public void actualizar(Usuario usuario) {
        repo.guardar(usuario);
    }

    public void eliminar(long id) {
        repo.listarTodos().removeIf(u -> u.getId() == id);
    }
}
