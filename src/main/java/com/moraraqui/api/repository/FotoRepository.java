package com.moraraqui.api.repository;

import com.moraraqui.api.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findByImovelIdOrderByOrdemAsc(Long imovelId);
}