package com.example.stockmarketspringapi.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserDto {
    //private Long id;
    private String username;
    private String email;
    private String passwordHash;

}
