package com.moraraqui.api.repository;

import com.moraraqui.api.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByCidadeId(Long cidadeId);
}