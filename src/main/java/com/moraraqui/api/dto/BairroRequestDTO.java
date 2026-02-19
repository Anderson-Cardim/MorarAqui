package com.moraraqui.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BairroRequestDTO(
        @NotBlank(message = "O nome do bairro é obrigatório")
        String nome,

        @NotNull(message = "O ID da cidade é obrigatório")
        Long cidadeId
) {}
