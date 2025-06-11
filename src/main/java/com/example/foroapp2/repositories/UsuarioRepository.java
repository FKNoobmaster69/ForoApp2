package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.utils.JsonFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private final String filePath = "data/usuarios.json";

    public List<Usuario> listarTodos() {
        return JsonFileUtils.cargarDesdeArchivo(filePath, Usuario[].class)
                .map(Arrays::asList)
                .orElse(new ArrayList<>());
    }

    public void guardar(Usuario usuario) {
        List<Usuario> usuarios = new ArrayList<>(listarTodos());

        // Si el usuario ya existe (tiene ID), actualizar
        if (usuario.getId() > 0) {
            usuarios.removeIf(u -> u.getId() == usuario.getId());
        } else {
            // Si es nuevo, asignar ID
            usuario.setId(generarNuevoId(usuarios));
        }

        usuarios.add(usuario);
        JsonFileUtils.guardarEnArchivo(filePath, usuarios);
    }

    public Optional<Usuario> buscarPorId(long id) {
        return listarTodos().stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public Optional<Usuario> buscarPorNombre(String nombre) {
        return listarTodos().stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return listarTodos().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean eliminar(long id) {
        List<Usuario> usuarios = new ArrayList<>(listarTodos());
        boolean removido = usuarios.removeIf(u -> u.getId() == id);
        if (removido) {
            JsonFileUtils.guardarEnArchivo(filePath, usuarios);
        }
        return removido;
    }

    private long generarNuevoId(List<Usuario> usuarios) {
        return usuarios.stream()
                .mapToLong(Usuario::getId)
                .max()
                .orElse(0) + 1;
    }
}
