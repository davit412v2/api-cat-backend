package com.example.catbackend.catbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.catbackend.catbackend.persistence.DTO.BreedCatResponse;
import com.example.catbackend.catbackend.persistence.DTO.ImageBreedCat;
import com.example.catbackend.catbackend.service.BreedCatService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para exponer endpoints relacionados con razas de gatos.
 * Se encarga de manejar las solicitudes HTTP y delegar la lógica al servicio.
 */

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
public class CatController {

    /**
     * Servicio que gestiona la lógica relacionada con las razas de gatos.
     */
    private final BreedCatService service;

    /**
     * Obtiene todas las razas de gatos disponibles.
     *
     * @return una lista de BreedCatResponse
     */
    @GetMapping
    public BreedCatResponse[] getAllBreeds() {
        return service.getAllBreedCats();
    }

    /**
     * Obtiene una raza específica de gato por su ID.
     *
     * @param id ID de la raza
     * @return objeto BreedCatResponse
     */
    @GetMapping("/{id}")
    public BreedCatResponse getBreedCatById(@PathVariable("id") String id) {
        return service.getBreedCatById(id);
    }

    /**
     * Busca razas de gatos por nombre.
     *
     * @param name nombre o parte del nombre de la raza
     * @return una lista de BreedCatResponse
     */
    @GetMapping("/search")
    public BreedCatResponse[] searchBreedCats(@RequestParam("name") String name) {
        return service.searchBreedsByName(name);
    }

    /**
     * Busca imágenes asociadas a una raza de gato específica por su ID.
     *
     * @param id ID de la raza
     * @return un arreglo de objetos ImageBreedCat
     */
    @GetMapping("/image")
    public ImageBreedCat[] searchImageBreedCats(@RequestParam("id") String id) {
        return service.searchImageBreedCats(id);
    }
}