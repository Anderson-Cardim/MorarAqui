package com.moraraqui.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CidadeRequestDTO(
        @NotBlank(message = "O nome da cidade é obrigatório")
        String nome,

        @NotNull(message = "O ID do estado é obrigatório")
        Long estadoId
) {}
