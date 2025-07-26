package com.example.catbackend.catbackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.catbackend.catbackend.apiexternal.TheCatApi;
import com.example.catbackend.catbackend.persistence.DTO.BreedCatResponse;
import com.example.catbackend.catbackend.persistence.DTO.ImageBreedCat;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreedCatService {

    private final TheCatApi api;

    public BreedCatResponse[] getAllBreedCats() {
        ResponseEntity<BreedCatResponse[]> response = api.getAllBreedsCats();

        return response.getBody();
    }

    public BreedCatResponse getBreedCatById(String id) {
        ResponseEntity<BreedCatResponse> response = api.getBreedCatById(id);
        return response.getBody();
    }

    public BreedCatResponse[] searchBreedsByName(String name) {
        ResponseEntity<BreedCatResponse[]> response = api.searchBreedsByName(name);
        return response.getBody();
    }

    public ImageBreedCat[] searchImageBreedCats(String id) {
        ResponseEntity<ImageBreedCat[]> response = api.searchImageBreedCatById(id);
        return response.getBody();
    }
}
