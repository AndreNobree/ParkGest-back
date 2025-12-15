package com.albin.parkgest.controller;

import com.albin.parkgest.dto.user.UserLoginDTO;
import com.albin.parkgest.dto.user.UserRegisterDTO;
import com.albin.parkgest.dto.user.UserResponseDTO;
import com.albin.parkgest.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto) {
        UserResponseDTO createdUser = userService.registerDefault(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
        UserResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
}