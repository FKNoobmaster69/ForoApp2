package com.example.foroapp2.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> leerArchivo(String ruta, Class<T[]> clase) {
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                return new ArrayList<>();
            }
            T[] array = mapper.readValue(archivo, clase);
            List<T> lista = new ArrayList<>();
            for (T elemento : array) {
                lista.add(elemento);
            }
            return lista;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static <T> void guardarArchivo(String ruta, List<T> lista) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ruta), lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
