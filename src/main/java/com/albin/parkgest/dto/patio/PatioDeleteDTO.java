package com.albin.parkgest.dto.patio;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatioDeleteDTO {
    @NotNull
    private Long patioId;
}
