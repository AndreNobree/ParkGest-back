package com.albin.parkgest.dto.planoFidelidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanoFidelidadeRegisterDTO {
    @NotNull
    private Long clienteId;

    private BigDecimal valor;

    @NotBlank
    private String metodo;

    private Integer quantidade;
}
