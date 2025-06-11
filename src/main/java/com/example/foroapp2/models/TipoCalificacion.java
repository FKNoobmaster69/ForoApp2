package com.example.foroapp2.models;

public enum TipoCalificacion {
    BUENA(1,  "Buena calificación"),
    REGULAR(0, "Calificación regular"),
    MALA(-1,   "Mala calificación");

    private final int puntuacion;
    private final String descripcion;

    TipoCalificacion(int puntuacion, String descripcion) {
        this.puntuacion  = puntuacion;
        this.descripcion = descripcion;
    }

    public int getPuntuacion()     { return puntuacion; }
    public String getDescripcion() { return descripcion; }
}
