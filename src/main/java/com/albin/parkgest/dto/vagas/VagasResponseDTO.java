package com.albin.parkgest.dto.vagas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagasResponseDTO {
    private String vaga;
    private String tipo;

    public VagasResponseDTO(String vaga, String tipo){
        this.vaga = vaga;
        this.tipo = tipo;
    }
}
