package com.albin.parkgest.dto.clientes;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientesRegisterDTO {
    @NotBlank
    private String nome;

    private String telefone;

    private String email;

}
