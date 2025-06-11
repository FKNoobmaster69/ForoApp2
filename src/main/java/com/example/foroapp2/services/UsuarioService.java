package com.example.foroapp2.services;

import com.example.foroapp2.models.Usuario;
import com.example.foroapp2.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void registrar(Usuario usuario) {
        usuarioRepository.guardar(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }


    public Optional<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.buscarPorNombre(nombre);
    }

    public void actualizarUsuario(Long id, Usuario usuarioActualizado) {
        buscarPorId(id).ifPresent(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setDescripcion(usuarioActualizado.getDescripcion());
            usuario.setGenero(usuarioActualizado.getGenero());
            usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            usuario.setRedesSociales(usuarioActualizado.getRedesSociales());
            usuarioRepository.guardar(usuario); // Actualizar en el repositorio
        });
    }

    public boolean eliminarUsuario(Long id) {
        return usuarioRepository.eliminar(id);
    }

    public Optional<Usuario> validarCredenciales(String email, String contrasena) {
        return usuarioRepository.listarTodos().stream()
                .filter(u -> u.getEmail().equals(email) && u.getContrasena().equals(contrasena))
                .findFirst();
    }
}
