package com.albin.parkgest.dto.clientes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientesResponseDTO {
    private Long id;
    private String nome;

    private String telefone;


    public ClientesResponseDTO(Long id, String nome, String telefone){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }
}
