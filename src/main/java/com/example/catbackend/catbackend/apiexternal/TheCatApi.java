package com.example.catbackend.catbackend.apiexternal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.catbackend.catbackend.persistence.DTO.BreedCatResponse;
import com.example.catbackend.catbackend.persistence.DTO.ImageBreedCat;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TheCatApi {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${thecatapi.url}")
    private String baseUrl;

    @Value("${thecatapi.key}")
    private String apiKey;

    public ResponseEntity<BreedCatResponse[]> getAllBreedsCats() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/breeds",
                HttpMethod.GET,
                entity,
                BreedCatResponse[].class);
    }

    public ResponseEntity<BreedCatResponse> getBreedCatById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/breeds/" + id,
                HttpMethod.GET,
                entity,
                BreedCatResponse.class);
    }

     public ResponseEntity<BreedCatResponse[]> searchBreedsByName(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/breeds/search?q=" + name,
                HttpMethod.GET,
                entity,
                BreedCatResponse[].class
        );
    }

    public ResponseEntity<ImageBreedCat[]> searchImageBreedCatById(String id){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
            baseUrl + "/images/search?breed_ids=" + id,
            HttpMethod.GET,
            entity,
            ImageBreedCat[].class
        );
    }
}
