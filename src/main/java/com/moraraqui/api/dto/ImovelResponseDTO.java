package com.moraraqui.api.dto;

import com.moraraqui.api.model.Imovel;
import java.math.BigDecimal;
import java.util.List;

public record ImovelResponseDTO(
        Long id,
        String titulo,
        String descricao,
        BigDecimal preco,
        Double area,
        Integer quartos,
        Integer banheiros,
        Integer vagas,
        String pontoReferencia,
        String nomeBairro,
        String nomeCidade,
        String nomeAnunciante,
        String whatsappAnunciante,
        String tipo,
        String finalidade,
        List<FotoResponseDTO> fotos
) {

    public ImovelResponseDTO(Imovel imovel) {
        this(
                imovel.getId(),
                imovel.getTitulo(),
                imovel.getDescricao(),
                imovel.getPreco(),
                imovel.getArea(),
                imovel.getQuartos(),
                imovel.getBanheiros(),
                imovel.getVagas(),
                imovel.getPontoReferencia(),
                imovel.getBairro().getNome(),
                imovel.getCidade().getNome(),
                imovel.getAnunciante().getNome(),
                imovel.getAnunciante().getWhatsapp(),
                imovel.getTipo().toString(),
                imovel.getFinalidade().toString(),
                imovel.getFotos() == null ? List.of() : imovel.getFotos().stream().map(FotoResponseDTO::new).toList()        );
    }
}