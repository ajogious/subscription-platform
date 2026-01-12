package com.ajogious.backend.dto;

import lombok.*;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
}