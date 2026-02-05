package com.albin.parkgest.controller;

import com.albin.parkgest.dto.clientes.ClientesRegisterDTO;
import com.albin.parkgest.dto.clientes.ClientesResponseDTO;
import com.albin.parkgest.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/all")
    public ResponseEntity<List<ClientesResponseDTO>> clientes(){
        List<ClientesResponseDTO> clientes = clientesService.clientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/register")
    public ResponseEntity<ClientesResponseDTO> novoCliente(@Valid @RequestBody ClientesRegisterDTO dto){
        ClientesResponseDTO criaCliente = clientesService.cadastroClientes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criaCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clientesService.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientesResponseDTO> editaCliente(@PathVariable Long id, @Valid @RequestBody ClientesRegisterDTO dto) {
        ClientesResponseDTO atualizado = clientesService.editaCliente(id, dto);
        return ResponseEntity.ok(atualizado);
    }


}
