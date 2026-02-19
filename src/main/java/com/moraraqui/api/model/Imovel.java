package com.moraraqui.api.model;

import com.moraraqui.api.dto.DadosAtualizacaoImovel;
import com.moraraqui.api.dto.ImovelRequestDTO;
import com.moraraqui.api.model.enums.FinalidadeImovel;
import com.moraraqui.api.model.enums.TipoImovel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "imoveis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("ativo = true")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    private Double area;
    private Integer quartos;
    private Integer banheiros;
    private Integer vagas;

    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario anunciante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bairro_id", nullable = false)
    private Bairro bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Foto> fotos =  new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TipoImovel tipo;

    @Enumerated(EnumType.STRING)
    private FinalidadeImovel finalidade;

    private boolean ativo;

    public Imovel (ImovelRequestDTO dto) {
        this.titulo = dto.titulo();
        this.area = dto.area();
        this.preco = dto.preco();
        this.quartos = dto.quartos();
        this.banheiros = dto.banheiros();
        this.vagas = dto.vagas();
        this.descricao = dto.descricao();
        this.pontoReferencia = dto.pontoReferencia();
        this.finalidade = dto.finalidade();
        this.tipo = dto.tipo();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoImovel dados) {
        if (dados.titulo() != null && !dados.titulo().isBlank()) {
            this.titulo = dados.titulo();
        }
        if (dados.descricao() != null && !dados.descricao().isBlank()) {
            this.descricao = dados.descricao();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
        if (dados.quartos() != null) {
            this.quartos = dados.quartos();
        }
        if (dados.banheiros() != null) {
            this.banheiros = dados.banheiros();
        }
        if (dados.vagas() != null) {
            this.vagas = dados.vagas();
        }
    }
}
