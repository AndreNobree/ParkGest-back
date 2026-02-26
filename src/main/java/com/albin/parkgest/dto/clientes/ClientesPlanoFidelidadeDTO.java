package com.albin.parkgest.dto.clientes;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClientesPlanoFidelidadeDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String metodo;
    private BigDecimal valor;

    public ClientesPlanoFidelidadeDTO(Long id, String nome, String telefone, String metodo, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.metodo = metodo;
        this.valor = valor;
    }

}
