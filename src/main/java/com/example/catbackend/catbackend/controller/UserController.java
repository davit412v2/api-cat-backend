package com.example.catbackend.catbackend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catbackend.catbackend.persistence.DTO.LoginRequest;
import com.example.catbackend.catbackend.persistence.DTO.LoginResponse;
import com.example.catbackend.catbackend.persistence.DTO.UserDTO;
import com.example.catbackend.catbackend.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para la gestión de usuarios.
 * Expone endpoints para el registro y autenticación de usuarios.
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    /**
     * Servicio que gestiona la lógica relacionada con usuarios.
     */
    private final UserService service;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user Objeto UserDTO con la información del nuevo usuario
     * @return ResponseEntity con el estado de la operación
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        try {
            service.register(user);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Autentica a un usuario en el sistema.
     *
     * @param request Objeto LoginRequest con las credenciales del usuario
     * @return un objeto ResponseEntity con el token de autenticación o mensaje de error
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = service.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}