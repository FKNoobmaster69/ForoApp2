package com.example.foroapp2.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tema {
    private long id;
    private String nombre;
    private String descripcion;
    private Comunidad comunidad;
    private LocalDateTime fechaCreacion;
    private List<Post> posts;

    public Tema() {
        this.fechaCreacion = LocalDateTime.now();
        this.posts = new ArrayList<>();
    }

    public Tema(String nombre, String descripcion, Comunidad comunidad) {
        this();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.comunidad = comunidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void agregarPost(Post post) {
        this.posts.add(post);
    }

    public void eliminarPost(Post post) {
        this.posts.remove(post);
    }

    @Override
    public String toString() {
        return "Tema{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", comunidad=" + (comunidad != null ? comunidad.getNombre() : "ninguna") +
                '}';
    }
}
