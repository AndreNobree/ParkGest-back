package com.albin.parkgest.dto.valorhora;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ValorHoraResponseDTO {
    private BigDecimal valorHora;

    private String tipoVeiculo;

    public ValorHoraResponseDTO(BigDecimal valorHora, String tipoVeiculo){
        this.valorHora = valorHora;
        this.tipoVeiculo = tipoVeiculo;
    }
}
