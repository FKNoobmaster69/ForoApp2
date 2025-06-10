package com.example.foroapp2.models;

public enum TipoCalificacion {
    BUENA(1, "Buena calificación"),
    REGULAR(0, "Calificación regular"),
    MALA(-1, "Mala calificación");

    private final int puntuacion;
    private final String comentario;

    TipoCalificacion(int puntuacion, String comentario) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }
}