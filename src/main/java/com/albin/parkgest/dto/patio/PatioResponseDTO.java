package com.albin.parkgest.dto.patio;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatioResponseDTO {
    private Long patioId;

    private String modeloCor;

    private String placa;

    private String tipo;

    private Long clienteId;

    private LocalDateTime horaEntrada;

    private Long valoresHoraId;

    private String nomeVaga;


    //home
    public PatioResponseDTO(Long patioId, String modeloCor, String placa, String tipo,
                            LocalDateTime horaEntrada, String nomeVaga) {

        this.patioId = patioId;
        this.modeloCor = modeloCor;
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
        this.nomeVaga = nomeVaga;
    }
}
