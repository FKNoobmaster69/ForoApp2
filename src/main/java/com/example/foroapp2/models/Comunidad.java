package com.example.foroapp2.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comunidad {

    private Long id;
    private String nombre;
    private String descripcion;
    private Usuario creador;
    private LocalDateTime fechaCreacion;
    private List<Tema> temas;
    private List<Usuario> miembros;

    public Comunidad() {
        this.fechaCreacion = LocalDateTime.now();
        this.temas     = new ArrayList<>();
        this.miembros  = new ArrayList<>();
    }

    public Comunidad(String nombre, String descripcion, Usuario creador) {
        this();
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.creador     = creador;
        this.miembros.add(creador);
    }

    public Comunidad(Long id, String nombre) {
        this();
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Usuario getCreador() { return creador; }
    public void setCreador(Usuario creador) { this.creador = creador; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public List<Tema> getTemas() { return temas; }
    public void setTemas(List<Tema> temas) { this.temas = temas; }

    public List<Usuario> getMiembros() { return miembros; }
    public void setMiembros(List<Usuario> miembros) { this.miembros = miembros; }

    public void agregarTema(Tema tema) {
        if (!temas.contains(tema)) temas.add(tema);
    }

    public void agregarMiembro(Usuario usuario) {
        if (!miembros.contains(usuario)) miembros.add(usuario);
    }

    public void eliminarMiembro(Usuario usuario) {
        miembros.remove(usuario);
    }
}
