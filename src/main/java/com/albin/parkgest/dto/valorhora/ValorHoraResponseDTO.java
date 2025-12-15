package com.albin.parkgest.dto.valorhora;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ValorHoraResponseDTO {
    private Long vagaId;

    private BigDecimal valorHora;

    public ValorHoraResponseDTO(BigDecimal valorHora){
        this.valorHora = valorHora;
    }
}
