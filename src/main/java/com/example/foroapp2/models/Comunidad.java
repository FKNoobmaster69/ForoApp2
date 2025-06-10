package com.example.foroapp2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Comunidad {
    private Long id;
    private String nombre;
    private String descripcion;
    private String logo;
    private boolean nsfw;
    private Usuario creador;
    private List<Tag> tags = new ArrayList<>();

    public Comunidad() {}

    public Comunidad(String nombre, String descripcion, String logo, boolean nsfw, Usuario creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.nsfw = nsfw;
        this.creador = creador;
    }

    public Comunidad(String nombre, String descripcion, Usuario creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.logo = "";
        this.nsfw = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comunidad)) return false;
        Comunidad comunidad = (Comunidad) o;
        return Objects.equals(id, comunidad.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
