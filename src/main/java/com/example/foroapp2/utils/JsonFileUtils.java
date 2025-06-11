package com.example.foroapp2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JsonFileUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private static String limpiarJsonRedesSociales(String json) {
        json = json.replaceAll("\"redesSociales\"\\s*:\\s*\"[^\"]*\"", "\"redesSociales\":{}");
        json = json.replaceAll("\"redesSociales\"\\s*:\\s*null", "\"redesSociales\":{}");
        return json;
    }

    public static <T> Optional<T> cargarDesdeArchivo(String ruta, Class<T> clazz) {
        try {
            File file = new File(ruta);
            if (!file.exists()) {
                return Optional.empty();
            }
            String json = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            json = limpiarJsonRedesSociales(json);
            T objeto = objectMapper.readValue(json, clazz);
            return Optional.of(objeto);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <T> Optional<List<T>> cargarListaDesdeArchivo(String ruta, Class<T[]> arrayClass) {
        try {
            File file = new File(ruta);
            if (!file.exists()) {
                return Optional.empty();
            }
            String json = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            json = limpiarJsonRedesSociales(json);
            T[] array = objectMapper.readValue(json, arrayClass);
            return Optional.of(Arrays.asList(array));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <T> void guardarEnArchivo(String ruta, T objeto) {
        try {
            File file = new File(ruta);
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
