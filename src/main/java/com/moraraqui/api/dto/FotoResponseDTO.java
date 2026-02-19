package com.moraraqui.api.dto;

import com.moraraqui.api.model.Foto;

public record FotoResponseDTO(
        Long id,
        String urlCompleta,
        Integer ordem
) {
    public FotoResponseDTO(Foto foto) {
        this(
                foto.getId(),
                "http://localhost:8080/uploads/" + foto.getUrl(),
                foto.getOrdem()
        );
    }
}