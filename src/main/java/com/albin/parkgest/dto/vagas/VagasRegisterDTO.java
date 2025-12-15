package com.albin.parkgest.dto.vagas;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagasRegisterDTO {
    @NotBlank
    private String vaga;

    @NotBlank
    private String tipo;
}
