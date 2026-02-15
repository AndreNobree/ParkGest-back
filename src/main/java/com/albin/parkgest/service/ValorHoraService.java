package com.albin.parkgest.service;

import com.albin.parkgest.dto.valorhora.ValorHoraRegisterDTO;
import com.albin.parkgest.dto.valorhora.ValorHoraResponseDTO;
import com.albin.parkgest.model.User;
import com.albin.parkgest.model.ValorHora;
import com.albin.parkgest.repository.UserRepository;
import com.albin.parkgest.repository.ValoresHoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValorHoraService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValoresHoraRepository valoresHoraRepository;

    public ValorHoraResponseDTO cadastraValorHora(ValorHoraRegisterDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (valoresHoraRepository.existsByTipoVeiculos(dto.getTipoVeiculo())) {
            throw new RuntimeException("Tipo veículo já cadastrado");
        }

        ValorHora valorHora = new ValorHora();
        valorHora.setValorHora(dto.getValorHora());
        valorHora.setTipoVeiculos(dto.getTipoVeiculo());
        valorHora.setCreatedAt(LocalDateTime.now());
        valorHora.setCreatedBy(user.getId());

        ValorHora salvaValorHora = valoresHoraRepository.save(valorHora);

        return new ValorHoraResponseDTO(salvaValorHora.getValorHora(), salvaValorHora.getTipoVeiculos());
    }

    public List<ValorHoraResponseDTO> retornaValoresHora(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<ValorHora> valorHoraList = valoresHoraRepository.findAll();

        return valorHoraList.stream()
                .map(p -> new ValorHoraResponseDTO(
                        p.getValorHora(),
                        p.getTipoVeiculos()
                ))
                .toList();
    }

    @Transactional
    public ValorHoraResponseDTO editaValoresHora(Long id, ValorHoraRegisterDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ValorHora valoresHora = valoresHoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        valoresHora.setValorHora(dto.getValorHora());
        valoresHora.setTipoVeiculos(dto.getTipoVeiculo());
        valoresHora.setUpdatedAt(LocalDateTime.now());
        valoresHora.setUpdatedBy(user.getId());

        ValorHora salvo = valoresHoraRepository.save(valoresHora);

        return new ValorHoraResponseDTO(
                salvo.getValorHora(),
                salvo.getTipoVeiculos()
        );
    }
}
