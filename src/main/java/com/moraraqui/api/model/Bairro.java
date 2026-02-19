package com.moraraqui.api.model;

import com.moraraqui.api.dto.BairroRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bairros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @OneToMany(mappedBy = "bairro")
    private List<Imovel> imoveis;

    public Bairro(BairroRequestDTO dto, Cidade cidade) {
        this.nome = dto.nome();
        this.cidade = cidade;
    }

}
