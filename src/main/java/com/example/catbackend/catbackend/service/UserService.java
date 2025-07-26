package com.example.catbackend.catbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.catbackend.catbackend.persistence.DTO.LoginRequest;
import com.example.catbackend.catbackend.persistence.DTO.LoginResponse;
import com.example.catbackend.catbackend.persistence.DTO.UserDTO;
import com.example.catbackend.catbackend.persistence.entity.User;
import com.example.catbackend.catbackend.repository.UserRespository;
import com.example.catbackend.catbackend.security.JwtSecurity;

import lombok.RequiredArgsConstructor;

/**
 * Servicio que maneja las operaciones relacionadas con los usuarios,
 * incluyendo el registro y la autenticación.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserInterfaceService {

    private final UserRespository respository;
    private final PasswordEncoder passwordEncoder;
    private final JwtSecurity jwtSecurity;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user objeto UserDTO que contiene los datos del usuario a registrar
     */
    @Override
    public void register(UserDTO user) {
        User userBuilder = User.builder()
                .name(user.getName())
                .userName(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        respository.save(userBuilder);
    }

    /**
     * Autentica a un usuario y genera un token JWT si las credenciales son válidas.
     *
     * @param request objeto LoginRequest con el nombre de usuario y contraseña
     * @return un objeto LoginResponse con el token generado
     * @throws RuntimeException si el usuario no existe o la contraseña es incorrecta
     */
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = respository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtSecurity.generateToken(user.getUserName());
        return new LoginResponse(token);
    }
}