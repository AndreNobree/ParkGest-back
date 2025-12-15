package com.albin.parkgest.dto.valorhora;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ValorHoraRegisterDTO {
    @NotBlank
    private Long vagaId;

    @NotBlank
    private BigDecimal valorHora;
}
