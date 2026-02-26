package com.albin.parkgest.service;

import com.albin.parkgest.dto.planoFidelidade.PlanoFidelidadeRegisterDTO;
import com.albin.parkgest.dto.planoFidelidade.PlanoFidelidadeResponseDTO;
import com.albin.parkgest.model.PlanoFidelidade;
import com.albin.parkgest.model.User;
import com.albin.parkgest.model.Clientes;
import com.albin.parkgest.repository.ClientesRepository;
import com.albin.parkgest.repository.PlanoFidelidadeRepository;
import com.albin.parkgest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanoFidelidadeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanoFidelidadeRepository planoFidelidadeRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    public List<PlanoFidelidadeResponseDTO> retornaPlanoFidelidade(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<PlanoFidelidade> planoFidelidadeList = planoFidelidadeRepository.findAll();

        return planoFidelidadeList.stream()
                .map(p -> new PlanoFidelidadeResponseDTO(
                        p.getId(),
                        p.getValor(),
                        p.getMetodo(),
                        p.getCliente().getNome(),
                        p.getCliente().getTelefone()
                ))
                .toList();
    }

    @Transactional
    public PlanoFidelidadeResponseDTO adicionaPlano(PlanoFidelidadeRegisterDTO dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Clientes clientes = clientesRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        PlanoFidelidade planoFidelidade = new PlanoFidelidade();
        planoFidelidade.setCliente(clientes);
        planoFidelidade.setMetodo(dto.getMetodo());
        planoFidelidade.setValor(dto.getValor());
        planoFidelidade.setCreateAt(LocalDateTime.now());
        planoFidelidade.setCreateBy(user.getId());

        PlanoFidelidade salvaPlano = planoFidelidadeRepository.save(planoFidelidade);

        return new PlanoFidelidadeResponseDTO(
                salvaPlano.getId(),
                salvaPlano.getValor(),
                salvaPlano.getMetodo(),
                salvaPlano.getCliente().getNome(),
                salvaPlano.getCliente().getTelefone()
        );
    }
}
