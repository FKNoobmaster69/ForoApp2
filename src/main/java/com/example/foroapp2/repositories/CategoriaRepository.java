package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRepository {

    private final List<Categoria> categorias = new ArrayList<>();
    private long siguienteId = 1;

    public List<Categoria> listarTodos() {
        return new ArrayList<>(categorias);
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categorias.stream()
                .filter(c -> c.getId() != null && c.getId().equals(id))
                .findFirst();
    }

    public void guardar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(siguienteId++);
            categorias.add(categoria);
        } else {
            actualizar(categoria);
        }
    }

    public boolean actualizar(Categoria categoriaActualizada) {
        Optional<Categoria> optCategoria = buscarPorId(categoriaActualizada.getId());
        if (optCategoria.isPresent()) {
            Categoria categoria = optCategoria.get();
            categoria.setNombre(categoriaActualizada.getNombre());
            categoria.setDescripcion(categoriaActualizada.getDescripcion());
            return true;
        }
        return false;
    }

    public boolean eliminar(Long id) {
        return categorias.removeIf(c -> c.getId() != null && c.getId().equals(id));
    }
}
