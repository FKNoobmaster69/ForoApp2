package com.example.foroapp2.models;

public class CalificacionUsuario {
    private long id;
    private Usuario usuario;
    private int puntuacion;
    private String comentario;

    public CalificacionUsuario() {
    }

    public CalificacionUsuario(long id, Usuario usuario, int puntuacion, String comentario) {
        this.id = id;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
