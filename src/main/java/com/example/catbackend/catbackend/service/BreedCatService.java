package com.example.catbackend.catbackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.catbackend.catbackend.apiexternal.TheCatApi;
import com.example.catbackend.catbackend.persistence.DTO.BreedCatResponse;
import com.example.catbackend.catbackend.persistence.DTO.ImageBreedCat;

import lombok.RequiredArgsConstructor;

/**
 * Servicio encargado de manejar la lógica de negocio relacionada con razas de
 * gatos.
 * Se comunica con la API externa TheCatApi para obtener datos de razas e
 * imágenes.
 */
@Service
@RequiredArgsConstructor
public class BreedCatService {

    // Cliente para consumir la API externa de gatos
    private final TheCatApi api;

    /**
     * Obtiene todas las razas de gatos disponibles desde TheCatApi.
     *
     * @return un arreglo de objetos BreedCatResponse que representan las razas
     */
    public BreedCatResponse[] getAllBreedCats() {
        ResponseEntity<BreedCatResponse[]> response = api.getAllBreedsCats();
        return response.getBody();
    }

    /**
     * Obtiene la información de una raza de gato específica por su ID.
     *
     * @param id identificador único de la raza
     * @return un objeto BreedCatResponse con la información de la raza
     */
    public BreedCatResponse getBreedCatById(String id) {
        ResponseEntity<BreedCatResponse> response = api.getBreedCatById(id);
        return response.getBody();
    }

    /**
     * Busca razas de gatos por nombre.
     *
     * @param name nombre (o parte del nombre) de la raza a buscar
     * @return un arreglo de BreedCatResponse que coinciden con la búsqueda
     */
    public BreedCatResponse[] searchBreedsByName(String name) {
        ResponseEntity<BreedCatResponse[]> response = api.searchBreedsByName(name);
        return response.getBody();
    }

    /**
     * Obtiene imágenes de gatos asociadas a una raza específica.
     *
     * @param id identificador de la raza
     * @return un arreglo de objetos ImageBreedCat con imágenes de gatos de esa raza
     */
    public ImageBreedCat[] searchImageBreedCats(String id) {
        ResponseEntity<ImageBreedCat[]> response = api.searchImageBreedCatById(id);
        return response.getBody();
    }
}