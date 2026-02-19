package com.moraraqui.api.dto;

import com.moraraqui.api.model.Bairro;

public record BairroResponseDTO(
        Long id,
        String nome,
        String nomeCidade,
        String siglaEstado
) {
    public BairroResponseDTO(Bairro bairro) {
        this(
                bairro.getId(),
                bairro.getNome(),
                bairro.getCidade().getNome(),
                bairro.getCidade().getEstado().getSigla()
        );
    }
}
