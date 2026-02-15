package com.albin.parkgest.dto.valorhora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ValorHoraRegisterDTO {
    @NotNull
    private BigDecimal valorHora;

    @NotBlank
    private String tipoVeiculo;
}
