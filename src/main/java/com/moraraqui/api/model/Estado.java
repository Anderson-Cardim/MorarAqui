package com.moraraqui.api.model;

import com.moraraqui.api.dto.EstadoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "estados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(nullable = false, unique = true, length = 2)
    private String sigla;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Cidade> cidades;

    public Estado(EstadoRequestDTO estadoDTO) {
        this.nome = estadoDTO.nome().toUpperCase();
        this.sigla = estadoDTO.sigla().toUpperCase();
    }
}
