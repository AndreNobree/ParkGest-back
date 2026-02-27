package com.albin.parkgest.service;

import com.albin.parkgest.dto.clientes.ClientesPlanoFidelidadeDTO;
import com.albin.parkgest.dto.clientes.ClientesRegisterDTO;
import com.albin.parkgest.dto.clientes.ClientesResponseDTO;
import com.albin.parkgest.exception.BusinessException;
import com.albin.parkgest.model.Clientes;
import com.albin.parkgest.model.User;
import com.albin.parkgest.repository.ClientesRepository;
import com.albin.parkgest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    public List<ClientesPlanoFidelidadeDTO> listarClientesComPlano() {
        return clientesRepository.listarClientesComPlano();
    }


    public List<ClientesResponseDTO> clientes(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        List<Clientes> clientesList = clientesRepository.findAll();

        return clientesList.stream()
                .map( p-> new ClientesResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getTelefone()
                ))
                .toList();
    }
     public ClientesResponseDTO cadastroClientes(ClientesRegisterDTO dto){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String email = auth.getName();

         User user = userRepository.findByEmail(email)
                 .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

         if(clientesRepository.existsByNome(dto.getNome())){
             throw new BusinessException("Nome do cliente já foi cadastrado");
         }
         if(clientesRepository.existsByTelefone(dto.getTelefone())){
             throw new BusinessException("Telefone do cliente já foi cadastrado");
         }

         Clientes clientes = new Clientes();
         clientes.setNome(dto.getNome());
         clientes.setTelefone(dto.getTelefone());
         clientes.setCreateAt(LocalDateTime.now());
         //vagas.setUpdatedAt(LocalDateTime.now());
         clientes.setCreateBy(user.getId());

         Clientes salvaCliente = clientesRepository.save(clientes);

         return new ClientesResponseDTO(salvaCliente.getId(), salvaCliente.getNome(), salvaCliente.getTelefone());
     }

     public void deletaCliente(Long clienteID){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String email = auth.getName();

         User user = userRepository.findByEmail(email)
                 .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

         Clientes clientes = clientesRepository.findById(clienteID)
                 .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

         clientesRepository.delete(clientes);
     }

    @Transactional
    public ClientesResponseDTO editaCliente(Long id, ClientesRegisterDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        Clientes cliente = clientesRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setUpdateAt(LocalDateTime.now());
        cliente.setUpdateBy(user.getId());

        Clientes salvo = clientesRepository.save(cliente);

        return new ClientesResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTelefone()
        );
    }



}
