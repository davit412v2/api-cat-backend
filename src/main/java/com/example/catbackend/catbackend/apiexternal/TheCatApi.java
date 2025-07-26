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


/**
 * Componente que gestiona las peticiones HTTP hacia TheCatAPI.
 * Utiliza un RestTemplate para realizar solicitudes a los endpoints
 * públicos de la API, incluyendo razas, búsqueda por nombre y obtención de imágenes.
 */
@Component
@RequiredArgsConstructor
public class TheCatApi {

    /**
     * Cliente HTTP utilizado para hacer las peticiones.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * URL base de TheCatAPI, inyectada desde las propiedades.
     */
    @Value("${thecatapi.url}")
    private String baseUrl;

    /**
     * Clave de API para autenticar las peticiones a TheCatAPI.
     */
    @Value("${thecatapi.key}")
    private String apiKey;

    /**
     * Obtiene la lista completa de razas de gatos desde TheCatAPI.
     *
     * @return una respuesta HTTP con un lista de BreedCatResponse
     */
    public ResponseEntity<BreedCatResponse[]> getAllBreedsCats() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/breeds",
                HttpMethod.GET,
                entity,
                BreedCatResponse[].class
        );
    }

    /**
     * Obtiene la información de una raza específica de gato por su ID.
     *
     * @param id ID de la raza
     * @return una respuesta HTTP con un objeto BreedCatResponse
     */
    public ResponseEntity<BreedCatResponse> getBreedCatById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/breeds/" + id,
                HttpMethod.GET,
                entity,
                BreedCatResponse.class
        );
    }

    /**
     * Busca razas de gatos por nombre.
     *
     * @param name nombre o parte del nombre de la raza a buscar
     * @return una respuesta HTTP con una lista de BreedCatResponse
     */
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

    /**
     * Obtiene imágenes asociadas a una raza de gato específica.
     *
     * @param id ID de la raza
     * @return una respuesta HTTP con un arreglo de cImageBreedCat
     */
    public ResponseEntity<ImageBreedCat[]> searchImageBreedCatById(String id) {
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