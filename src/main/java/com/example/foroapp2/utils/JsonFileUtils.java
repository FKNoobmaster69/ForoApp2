package com.example.foroapp2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JsonFileUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static <T> Optional<T> cargarDesdeArchivo(String ruta, Class<T> clazz) {
        try {
            File file = new File(ruta);
            if (!file.exists()) {
                return Optional.empty();
            }
            T objeto = objectMapper.readValue(file, clazz);
            return Optional.of(objeto);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Método para cargar un array de objetos y convertirlo en lista
    public static <T> Optional<List<T>> cargarListaDesdeArchivo(String ruta, Class<T[]> arrayClass) {
        try {
            File file = new File(ruta);
            if (!file.exists()) {
                return Optional.empty();
            }
            T[] array = objectMapper.readValue(file, arrayClass);
            return Optional.of(Arrays.asList(array));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <T> void guardarEnArchivo(String ruta, T objeto) {
        try {
            File file = new File(ruta);
            // Crear directorios si no existen
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
