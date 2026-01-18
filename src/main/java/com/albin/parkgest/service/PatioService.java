package com.albin.parkgest.service;

import com.albin.parkgest.dto.patio.PatioDeleteDTO;
import com.albin.parkgest.dto.patio.PatioRegisterDTO;
import com.albin.parkgest.dto.patio.PatioResponseDTO;
import com.albin.parkgest.model.Patio;
import com.albin.parkgest.model.User;
import com.albin.parkgest.model.Vagas;
import com.albin.parkgest.repository.PatioRepository;
import com.albin.parkgest.repository.UserRepository;
import com.albin.parkgest.repository.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatioService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private VagasRepository vagasRepository;

    //vagas - home
    public List<PatioResponseDTO> vagasOcupadas(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Patio> patio = patioRepository.findAll();

        return patio.stream()
                .map(p -> new PatioResponseDTO(
                        p.getId(),
                        p.getModeloCor(),
                        p.getPlaca(),
                        p.getTipo(),
                        p.getHoraEntrada(),
                        p.getVaga().getVaga()
                ))
                .toList();
        //.getVaga().getVaga() (coluna vaga de patio e coluna vaga de vagas)
    }

    //tela de controle
    @Transactional //Garante atomicidade (ou tudo salva, ou nada)
    public PatioResponseDTO adicionaPatio(PatioRegisterDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Vagas vaga = vagasRepository.findById(dto.getVagaId())
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        vaga.setAcao("ocupada");
        vaga.setUpdatedBy(user.getId());
        vaga.setUpdatedAt(LocalDateTime.now());
        vagasRepository.save(vaga);

        Patio patio = new Patio();
        patio.setVaga(vaga);
        patio.setModeloCor(dto.getModeloCor());
        patio.setPlaca(dto.getPlaca());
        patio.setTipo(dto.getTipo());
        //patio.setCliente();
        patio.setHoraEntrada(LocalDateTime.now());
        //patio.setValorHora();
        patio.setCreatedBy(user.getId());

        Patio salvaPatio = patioRepository.save(patio);


        return new PatioResponseDTO(salvaPatio.getId(), salvaPatio.getModeloCor(), salvaPatio.getPlaca(), salvaPatio.getTipo(), salvaPatio.getHoraEntrada(), salvaPatio.getVaga().getVaga() );
    }

    @Transactional
    public void finalizaPatio(Long patioId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Patio patio = patioRepository.findById(patioId)
                .orElseThrow(() -> new RuntimeException("Patio não encontrado"));

        Vagas vaga = patio.getVaga();

        vaga.setAcao("livre");
        vaga.setUpdatedBy(user.getId());
        vaga.setUpdatedAt(LocalDateTime.now());
        vagasRepository.save(vaga);

        patioRepository.delete(patio);
    }


}
