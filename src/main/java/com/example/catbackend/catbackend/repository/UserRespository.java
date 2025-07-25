package com.example.catbackend.catbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catbackend.catbackend.persistence.entity.User;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User>  findByUserName(String userName);
}
