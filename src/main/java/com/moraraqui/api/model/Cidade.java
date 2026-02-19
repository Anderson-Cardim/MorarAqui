package com.moraraqui.api.model;

import com.moraraqui.api.dto.CidadeRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    private List<Bairro> bairros;

    public Cidade(CidadeRequestDTO dto, Estado estado) {
        this.nome = dto.nome();
        this.estado = estado;
    }
}