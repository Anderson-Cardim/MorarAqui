package com.moraraqui.api.repository;

import com.moraraqui.api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByEstadoId(Long estadoId);

    List<Cidade> findByNomeContainingIgnoreCase(String nome);
}
