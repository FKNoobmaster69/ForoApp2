package com.example.foroapp2.models;

import java.time.LocalDate;

public class Usuario {
    private long id;
    private String nombre;
    private String email;
    private String contrasena;
    private String descripcion;
    private String redesSociales;
    private LocalDate fechaNacimiento;
    private Genero genero;

    public Usuario() {}

    public Usuario(String nombre, String email, String contrasena, Genero genero) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.genero = genero;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getRedesSociales() { return redesSociales; }
    public void setRedesSociales(String redesSociales) { this.redesSociales = redesSociales; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
}
