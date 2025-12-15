package com.albin.parkgest.dto.clientes;

public class ClientesResponseDTO {
    private String nome;

    private String telefone;

    private String email;

    public ClientesResponseDTO(String nome, String telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
}
