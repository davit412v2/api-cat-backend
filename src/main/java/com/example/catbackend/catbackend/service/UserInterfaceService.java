package com.example.catbackend.catbackend.service;

import com.example.catbackend.catbackend.persistence.DTO.*;

public interface UserInterfaceService {

    void register(UserDTO user);

    LoginResponse login(LoginRequest request);

}
