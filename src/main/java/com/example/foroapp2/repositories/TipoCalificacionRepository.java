package com.example.foroapp2.repositories;

import com.example.foroapp2.models.TipoCalificacion;
import java.util.List;
import java.util.Optional;

public class TipoCalificacionRepository {
    private final List<TipoCalificacion> tipos = List.of(TipoCalificacion.values());

    public List<TipoCalificacion> listarTodos() {
        return tipos;
    }

    public Optional<TipoCalificacion> buscarPorOrdinal(int ordinal) {
        return tipos.stream()
                .filter(t -> t.ordinal() == ordinal)
                .findFirst();
    }

    public Optional<TipoCalificacion> buscarPorNombre(String nombre) {
        return tipos.stream()
                .filter(t -> t.name().equalsIgnoreCase(nombre))
                .findFirst();
    }
}
