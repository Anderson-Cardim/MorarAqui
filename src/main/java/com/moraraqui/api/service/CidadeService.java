package com.moraraqui.api.service;

import com.moraraqui.api.dto.CidadeRequestDTO;
import com.moraraqui.api.dto.CidadeResponseDTO;
import com.moraraqui.api.dto.EstadoRequestDTO;
import com.moraraqui.api.model.Cidade;
import com.moraraqui.api.model.Estado;
import com.moraraqui.api.repository.CidadeRepository;
import com.moraraqui.api.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public CidadeResponseDTO salvar(CidadeRequestDTO dto) {
        Estado estado = estadoRepository.findById(dto.estadoId())
                .orElseThrow(() -> new EntityNotFoundException("Não é possível encontrar o estado: Estado com ID " + dto.estadoId() + " não existe."));

        Cidade cidade = new Cidade(dto, estado);

        return new CidadeResponseDTO(cidadeRepository.save(cidade));
    }

    public List<CidadeResponseDTO> listarPorEstado(Long estadoId) {
        return cidadeRepository.findByEstadoId(estadoId).stream()
                .map(CidadeResponseDTO::new)
                .toList();
    }
}