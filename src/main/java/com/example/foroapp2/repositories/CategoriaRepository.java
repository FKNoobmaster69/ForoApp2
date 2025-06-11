package com.example.foroapp2.repositories;

import com.example.foroapp2.models.Categoria;
import com.example.foroapp2.utils.JsonFileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRepository {
    private final String filePath = "data/categorias.json";

    public List<Categoria> listarTodos() {
        return JsonFileUtils.cargarDesdeArchivo(filePath, Categoria[].class).map(List::of).orElse(new ArrayList<>());
    }

    public Optional<Categoria> buscarPorId(long id) {
        return listarTodos().stream().filter(c -> c.getId() == id).findFirst();
    }

    public void guardar(Categoria categoria) {
        synchronized (this) {
            List<Categoria> categorias = listarTodos();
            categoria.setId(generarNuevoId(categorias));
            categorias.add(categoria);
            JsonFileUtils.guardarEnArchivo(filePath, categorias);
        }
    }

    private long generarNuevoId(List<Categoria> categorias) {
        return categorias.stream().mapToLong(Categoria::getId).max().orElse(0) + 1;
    }
}
