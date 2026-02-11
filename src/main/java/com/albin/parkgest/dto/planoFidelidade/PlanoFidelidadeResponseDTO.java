package com.albin.parkgest.dto.planoFidelidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanoFidelidadeResponseDTO {
    private BigDecimal valor;

    private String metodo;

    private Integer quantidade;

    public PlanoFidelidadeResponseDTO(BigDecimal valor, String metodo, Integer quantidade){
        this.valor = valor;
        this.metodo = metodo;
        this.quantidade = quantidade;
    }

}
