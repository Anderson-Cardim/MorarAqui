package com.moraraqui.api.service;

import com.moraraqui.api.dto.EstadoRequestDTO;
import com.moraraqui.api.dto.EstadoResponseDTO;
import com.moraraqui.api.model.Estado;
import com.moraraqui.api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Estado salvar (EstadoRequestDTO estadoDTO) {
        Estado estado = new Estado(estadoDTO);

        return repository.save(estado);
    }

    public List<EstadoResponseDTO> listarTodos() {
        List<Estado> categorias = repository.findAll();

        return categorias.stream()
                .map(EstadoResponseDTO::new)
                .collect(Collectors.toList());
    }

}
