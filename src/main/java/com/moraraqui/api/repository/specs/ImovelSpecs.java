package com.moraraqui.api.repository.specs;

import com.moraraqui.api.model.Imovel;
import com.moraraqui.api.model.enums.TipoImovel;
import com.moraraqui.api.model.enums.FinalidadeImovel;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ImovelSpecs {

    public static Specification<Imovel> porCidade(Long cidadeId) {
        return (root, query, builder) ->
                cidadeId == null ? null : builder.equal(root.get("cidade").get("id"), cidadeId);
    }

    public static Specification<Imovel> porBairro(Long bairroId) {
        return (root, query, builder) ->
                bairroId == null ? null : builder.equal(root.get("bairro").get("id"), bairroId);
    }

    public static Specification<Imovel> porTipo(TipoImovel tipo) {
        return (root, query, builder) ->
                tipo == null ? null : builder.equal(root.get("tipo"), tipo);
    }

    public static Specification<Imovel> porFinalidade(FinalidadeImovel finalidade) {
        return (root, query, builder) ->
                finalidade == null ? null : builder.equal(root.get("finalidade"), finalidade);
    }

    public static Specification<Imovel> precoAte(BigDecimal precoMax) {
        return (root, query, builder) ->
                precoMax == null ? null : builder.lessThanOrEqualTo(root.get("preco"), precoMax);
    }

    public static Specification<Imovel> minimoQuartos(Integer quartos) {
        return (root, query, builder) -> {
            if (quartos == null) {
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("quartos"), quartos);
        };
    }
}