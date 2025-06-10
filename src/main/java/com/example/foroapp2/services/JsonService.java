package com.example.foroapp2.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JsonService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String DATA_DIR = "data/";

    static {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static <T> void saveToJson(List<T> objects, String fileName) {
        try {
            File file = new File(DATA_DIR + fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, objects);
        } catch (IOException e) {
            System.err.println("Error guardando " + fileName + ": " + e.getMessage());
        }
    }

    public static <T> List<T> loadFromJson(String fileName, Class<T> clazz) {
        try {
            File file = new File(DATA_DIR + fileName);
            if (!file.exists() || file.length() == 0) {
                return Collections.emptyList();
            }
            TypeFactory typeFactory = mapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, clazz);
            return mapper.readValue(file, collectionType);
        } catch (IOException e) {
            System.err.println("Error cargando " + fileName + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
