package com.example.foroapp2.models;

public class Tema {
    private long id;
    private String titulo;
    private String descripcion;
    private Comunidad comunidad;

    public Tema() {}

    public Tema(long id, String titulo, String descripcion, Comunidad comunidad) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.comunidad = comunidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
