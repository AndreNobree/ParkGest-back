package com.albin.parkgest.dto.patio;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatioRegisterDTO {
    @NotBlank
    private Long vagaId;

    private String modeloCor;

    @NotBlank
    private String placa;

    private String tipo;

    private Long clienteId;

    private LocalDateTime horaEntrada;

    private Long valoresHoraId;

    private String acao;
}
