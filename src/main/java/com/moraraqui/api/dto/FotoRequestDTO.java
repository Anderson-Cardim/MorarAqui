package com.moraraqui.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FotoRequestDTO(
        Integer ordem,

        @NotNull(message = "O ID do imóvel é obrigatório")
        Long imovelId
) {}
