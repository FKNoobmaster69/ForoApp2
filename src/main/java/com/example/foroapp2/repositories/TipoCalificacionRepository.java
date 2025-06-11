package com.example.foroapp2.repositories;

import com.example.foroapp2.models.TipoCalificacion;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TipoCalificacionRepository {

    public List<TipoCalificacion> listarTodos() {
        return Arrays.asList(TipoCalificacion.values());
    }

    public Optional<TipoCalificacion> buscarPorId(long puntuacion) {
        return listarTodos().stream()
                .filter(t -> t.getPuntuacion() == puntuacion)
                .findFirst();
    }

    public Optional<TipoCalificacion> buscarPorNombre(String nombre) {
        try {
            return Optional.of(TipoCalificacion.valueOf(nombre));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
