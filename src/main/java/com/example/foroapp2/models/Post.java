package com.example.foroapp2.models;


import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String titulo;
    private String contenido;
    private Usuario autor;
    private Comunidad comunidad;
    private LocalDateTime fechaCreacion;
    private int likes;
    private int dislikes;

    public Post() {
        this.fechaCreacion = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
    }

    public Post(String titulo, String contenido, Usuario autor, Comunidad comunidad) {
        this();
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.comunidad = comunidad;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "desconocido") +
                ", comunidad=" + (comunidad != null ? comunidad.getNombre() : "ninguna") +
                ", fecha=" + fechaCreacion +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
