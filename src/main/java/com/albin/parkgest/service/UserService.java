package com.albin.parkgest.service;

import com.albin.parkgest.dto.user.UserLoginDTO;
import com.albin.parkgest.dto.user.UserRegisterDTO;
import com.albin.parkgest.dto.user.UserResponseDTO;
import com.albin.parkgest.model.User;
import com.albin.parkgest.repository.UserRepository;
import com.albin.parkgest.security.JwtService;
import com.albin.parkgest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserResponseDTO login(UserLoginDTO dto){
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new UserResponseDTO(user.getEmail(), token);
    }

    public UserResponseDTO registerDefault(UserRegisterDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());

        String hash = passwordEncoder.encode(dto.getPassword());
        user.setPasswordHash(hash);

        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        user.setCreateBy(1L);
        user.setUpdateBy(1L);

        User saved = userRepository.save(user);

        String token = jwtUtil.generateToken(saved.getEmail());

        return new UserResponseDTO(saved.getEmail(), token);
    }


}
