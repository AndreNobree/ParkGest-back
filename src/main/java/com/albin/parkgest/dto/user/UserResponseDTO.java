package com.albin.parkgest.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String email;
    private String token;

    public UserResponseDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }
}