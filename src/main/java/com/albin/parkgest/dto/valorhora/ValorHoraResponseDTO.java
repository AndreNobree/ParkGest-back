package com.albin.parkgest.dto.valorhora;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ValorHoraResponseDTO {
    private Long id;
    private BigDecimal valorHora;

    private String tipoVeiculo;

    public ValorHoraResponseDTO(Long id, BigDecimal valorHora, String tipoVeiculo){
        this.id = id;
        this.valorHora = valorHora;
        this.tipoVeiculo = tipoVeiculo;
    }
}
