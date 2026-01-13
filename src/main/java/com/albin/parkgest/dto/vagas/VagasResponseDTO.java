package com.albin.parkgest.dto.vagas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagasResponseDTO {
    private Long id;
    private String vaga;

    public VagasResponseDTO(Long id, String vaga){
        this.id = id;
        this.vaga = vaga;
    }
}
