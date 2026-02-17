package com.albin.parkgest.dto.planoFidelidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanoFidelidadeResponseDTO {
    private Long id;
    private BigDecimal valor;

    private String metodo;

    private String cliente;

    public PlanoFidelidadeResponseDTO(Long id, BigDecimal valor, String metodo, String cliente){
        this.id = id;
        this.valor = valor;
        this.metodo = metodo;
        this.cliente = cliente;
    }

}
