package com.example.foroapp2.models;

import java.util.List;

public class Post {
    private Long id;
    private String titulo;
    private String contenido;
    private Usuario autor;
    private Comunidad comunidad;
    private List<Tag> tags;
    private int likes;
    private int dislikes;

    public Post() {}

    public Post(String titulo, String contenido, Usuario autor, Comunidad comunidad) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.comunidad = comunidad;
        this.likes = 0;
        this.dislikes = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
