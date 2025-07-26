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

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
public class CatController {
    private final BreedCatService service;

    @GetMapping
    public BreedCatResponse[] getAllBreeds(){
        return service.getAllBreedCats();
    }


    @GetMapping("/{id}")
    public BreedCatResponse getBreedCatById(@PathVariable("id") String id){
        return service.getBreedCatById(id);
    }

    @GetMapping("/search")
    public BreedCatResponse[] searchBreedCats(@RequestParam("name") String name){
        return service.searchBreedsByName(name);
    }

    @GetMapping("/image")
    public ImageBreedCat[] searchImageBreedCats(@RequestParam("id") String id){
            return service.searchImageBreedCats(id);
    }
}
