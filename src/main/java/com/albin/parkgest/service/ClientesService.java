package com.albin.parkgest.service;

import com.albin.parkgest.dto.clientes.ClientesRegisterDTO;
import com.albin.parkgest.dto.clientes.ClientesResponseDTO;
import com.albin.parkgest.model.Clientes;
import com.albin.parkgest.model.User;
import com.albin.parkgest.repository.ClientesRepository;
import com.albin.parkgest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    public List<ClientesResponseDTO> clientes(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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
                 .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

         if(clientesRepository.existsByNome(dto.getNome())){
             throw new RuntimeException("Nome do cliente já foi cadastrado");
         }
         if(clientesRepository.existsByTelefone(dto.getTelefone())){
             throw new RuntimeException("Telefone do cliente já foi cadastrado");
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
                 .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

         Clientes clientes = clientesRepository.findById(clienteID)
                 .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

         clientesRepository.delete(clientes);
     }
}
