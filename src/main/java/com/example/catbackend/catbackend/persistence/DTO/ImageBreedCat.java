package com.example.catbackend.catbackend.persistence.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ImageBreedCat {
    @JsonProperty("breeds")
    private List<BreedCatResponse> cfaUrl;
}
