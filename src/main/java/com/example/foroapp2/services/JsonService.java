package com.example.foroapp2.services;

import com.example.foroapp2.utils.JsonFileUtils;

import java.util.Optional;

public class JsonService {


    public <T> Optional<T> cargarJson(String ruta, Class<T> clazz) {
        return JsonFileUtils.cargarDesdeArchivo(ruta, clazz);
    }


    public <T> void guardarJson(String ruta, T objeto) {
        JsonFileUtils.guardarEnArchivo(ruta, objeto);
    }
}
