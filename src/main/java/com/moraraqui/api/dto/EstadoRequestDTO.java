package com.moraraqui.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO(
        @NotBlank(message = "O nome do estado é obrigatório")
        @Size(min = 4, max = 50, message = "O nome deve ter entre 4 e 50 caracteres")
        String nome,

        @NotBlank(message = "A sigla é obrigatória")
        @Size(min = 2, max = 2, message = "A sigla deve ter exatamente 2 caracteres")
        String sigla
) {
}
