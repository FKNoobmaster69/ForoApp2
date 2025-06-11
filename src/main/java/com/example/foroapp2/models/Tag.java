package com.example.foroapp2.models;

public class Tag {

    private Long id;
    private String nombre;

    public Tag() { }

    public Tag(String nombre) {
        this.nombre = nombre;
    }

    public Long getId()        { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre;
    }
}
