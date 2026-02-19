package com.moraraqui.api.dto;

import java.math.BigDecimal;

public record DadosAtualizacaoImovel(
        String titulo,
        String descricao,
        BigDecimal preco,
        Integer quartos,
        Integer banheiros,
        Integer vagas
) {}