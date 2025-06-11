package com.example.foroapp2.utils;

import com.example.foroapp2.models.Usuario;



    import com.example.foroapp2.models.Usuario;

public class SessionManager {

    private static Usuario usuarioActual = null;

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean hayUsuarioLogueado() {
        return usuarioActual != null;
    }
}
