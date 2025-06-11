package com.example.foroapp2.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.*;

public class Usuario {

    private long id;
    private String nombre;
    private String contrasena;
    private String email;
    private String descripcion;
    private Genero genero;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private Map<String, String> redesSociales;
    private List<Comunidad> comunidades;
    private List<Post> posts;

    public Usuario() {
        this.redesSociales = new HashMap<>();
        this.comunidades = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public Usuario(String nombre, String email, String contrasena, Genero genero) {
        this();
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.genero = genero;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Map<String, String> getRedesSociales() { return redesSociales; }
    public void setRedesSociales(Map<String, String> redesSociales) { this.redesSociales = redesSociales; }

    public List<Comunidad> getComunidades() { return comunidades; }
    public void setComunidades(List<Comunidad> comunidades) { this.comunidades = comunidades; }

    public List<Post> getPosts() { return posts; }
    public void setPosts(List<Post> posts) { this.posts = posts; }

    public void agregarComunidad(Comunidad comunidad) {
        if (!comunidades.contains(comunidad)) {
            comunidades.add(comunidad);
        }
    }

    public void eliminarComunidad(Comunidad comunidad) {
        comunidades.remove(comunidad);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", genero=" + genero +
                '}';
    }
}
