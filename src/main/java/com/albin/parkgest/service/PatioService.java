package com.albin.parkgest.service;

import com.albin.parkgest.dto.patio.PatioRegisterDTO;
import com.albin.parkgest.dto.patio.PatioResponseDTO;
import com.albin.parkgest.model.Patio;
import com.albin.parkgest.model.User;
import com.albin.parkgest.repository.PatioRepository;
import com.albin.parkgest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatioService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatioRepository patioRepository;

    //vagas - home
    public List<PatioResponseDTO> vagasOcupadas(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Patio> patio = patioRepository.findByAcao("estacionado");

        return patio.stream()
                .map(p -> new PatioResponseDTO(
                        p.getId(),
                        p.getModeloCor(),
                        p.getPlaca(),
                        p.getTipo(),
                        p.getHoraEntrada(),
                        p.getAcao(),
                        p.getVaga().getVaga()
                ))
                .toList();
        //.getVaga().getVaga() (coluna vaga de patio e coluna vaga de vagas)
    }

    //tela de controle
    public PatioResponseDTO adicionaPatio(PatioRegisterDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Patio patio = new Patio();
        patio.setVaga(patio.getVaga());
        patio.setModeloCor(dto.getModeloCor());
        patio.setPlaca(dto.getPlaca());
        patio.setTipo(dto.getTipo());
        //patio.setCliente();
        patio.setHoraEntrada(LocalDateTime.now());
        //patio.setValorHora();
        patio.setAcao(dto.getAcao());

        Patio salvaPatio = patioRepository.save(patio);

        return new PatioResponseDTO(salvaPatio.getId(), salvaPatio.getModeloCor(), salvaPatio.getPlaca(), salvaPatio.getTipo(), salvaPatio.getHoraEntrada(), salvaPatio.getAcao(), salvaPatio.getVaga().getVaga() );
    }

}
