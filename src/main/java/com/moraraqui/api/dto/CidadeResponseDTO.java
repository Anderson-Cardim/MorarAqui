package com.moraraqui.api.dto;

import com.moraraqui.api.model.Cidade;

public record CidadeResponseDTO(
        Long id,
        String nome,
        String nomeEstado,
        String siglaEstado
) {
    public CidadeResponseDTO(Cidade cidade) {
        this(
                cidade.getId(),
                cidade.getNome(),
                cidade.getEstado().getNome(),
                cidade.getEstado().getSigla()
        );
    }
}