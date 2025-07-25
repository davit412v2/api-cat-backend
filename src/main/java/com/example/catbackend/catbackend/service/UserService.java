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

@Service
@RequiredArgsConstructor
public class UserService implements UserInterfaceService {

    private final UserRespository respository;
    private final PasswordEncoder passwordEncoder;
    private final JwtSecurity jwtSecurity;

    @Override
    public void register(UserDTO user) {
        User userBuilder = User.builder()
                .name(user.getName())
                .userName(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        respository.save(userBuilder);
       
        System.out.println("Register...................");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        System.out.println("login...................");

        User user = respository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtSecurity.generateToken(user.getUserName());
        
           System.out.println("login with token...................");
        return new LoginResponse(token);
    }

}
