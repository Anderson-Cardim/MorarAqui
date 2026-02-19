package com.moraraqui.api.repository;

import com.moraraqui.api.model.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long>, JpaSpecificationExecutor<Imovel> {
    Page<Imovel> findAllByAnuncianteId(Long id, Pageable paginacao);

    @Query("SELECT i FROM Imovel i LEFT JOIN FETCH i.fotos WHERE i.id = :id")
    Optional<Imovel> findByIdWithFotos(Long id);

    @EntityGraph(attributePaths = "fotos")
    Page<Imovel> findAll(Pageable pageable);

}