# Foro App 2 - Aplicación de Foro con JavaFX

Aplicación de foro de discusión desarrollada con Java y JavaFX que permite a los usuarios registrarse, iniciar sesión, crear y participar en comunidades y discusiones.

## Características

- Registro e inicio de sesión de usuarios
- Creación y participación en comunidades
- Publicación de temas y comentarios
- Perfil de usuario personalizable
- Sistema de likes/dislikes
- Interfaz gráfica moderna con JavaFX

## Requisitos

- Java SDK 23 o superior
- JavaFX
- Maven (opcional, para gestión de dependencias)

## Estructura del Proyecto

- `src/main/java/com/example/foroapp2/`: Código fuente Java
  - `controllers/`: Controladores de las vistas
  - `models/`: Modelos de datos
  - `repositories/`: Acceso a datos
  - `services/`: Lógica de negocio
  - `utils/`: Utilidades
- `src/main/resources/`: Recursos (FXML, CSS, imágenes)

## Ejecución

Para ejecutar la aplicación:

1. Asegúrate de tener configurado correctamente Java y JavaFX
2. Ejecuta la clase `Main.java`

## Desarrollo

Este proyecto sigue el patrón MVC (Modelo-Vista-Controlador):
- Modelos: Clases que representan los datos (Usuario, Post, etc.)
- Vistas: Archivos FXML que definen la interfaz
- Controladores: Clases Java que manejan la lógica de la interfaz

Los datos se almacenan en archivos JSON en el directorio `data/`.

## Licencia

Este proyecto es de código abierto bajo la licencia MIT.
