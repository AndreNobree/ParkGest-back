package com.albin.parkgest.dto.patio;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PatioResponseDTO {
    private Long vagaId;

    private String modeloCor;

    private String placa;

    private String tipo;

    private Long clienteId;

    private LocalDateTime horaEntrada;

    private Long valoresHoraId;

    private String acao;

    public PatioResponseDTO(String modeloCor, String placa, String tipo,
                            LocalDateTime horaEntrada, String acao) {

        this.modeloCor = modeloCor;
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
        this.acao = acao;
    }
}
