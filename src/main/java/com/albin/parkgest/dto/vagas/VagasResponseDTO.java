package com.albin.parkgest.dto.vagas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagasResponseDTO {
    private Long id;
    private String vaga;
    private String tipo;

    public VagasResponseDTO(Long id, String vaga, String tipo){
        this.id = id;
        this.vaga = vaga;
        this.tipo = tipo;
    }
}
