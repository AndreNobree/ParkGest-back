package com.albin.parkgest.service;

import com.albin.parkgest.dto.vagas.VagasRegisterDTO;
import com.albin.parkgest.dto.vagas.VagasResponseDTO;
import com.albin.parkgest.model.User;
import com.albin.parkgest.model.Vagas;
import com.albin.parkgest.repository.UserRepository;
import com.albin.parkgest.repository.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

@Service
public class VagasService {

    @Autowired
    private VagasRepository vagasRepository;

    @Autowired
    private UserRepository userRepository;

    public VagasResponseDTO cadastraVaga(VagasRegisterDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(vagasRepository.existsByVaga(dto.getVaga())){
            throw new RuntimeException("O nome da vaga já existe");
        }

        Vagas vagas = new Vagas();
        vagas.setVaga(dto.getVaga());
        vagas.setTipo(dto.getTipo());
        vagas.setCreatedAt(LocalDateTime.now());
        vagas.setUpdatedAt(LocalDateTime.now());
        vagas.setCreatedBy(user.getId());

        Vagas salvaVaga = vagasRepository.save(vagas);

        return new VagasResponseDTO(salvaVaga.getVaga(), salvaVaga.getTipo());
    }
}
