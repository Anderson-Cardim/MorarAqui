package com.moraraqui.api.dto;

import com.moraraqui.api.model.enums.TipoImovel;
import com.moraraqui.api.model.enums.FinalidadeImovel;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ImovelRequestDTO(
        @NotBlank String titulo,
        String descricao,
        @NotNull @Positive BigDecimal preco,
        Double area,
        Integer quartos,
        Integer banheiros,
        Integer vagas,
        String pontoReferencia,

        @NotNull Long usuarioId,
        @NotNull Long bairroId,
        @NotNull Long cidadeId,

        @NotNull TipoImovel tipo,
        @NotNull FinalidadeImovel finalidade
) {}