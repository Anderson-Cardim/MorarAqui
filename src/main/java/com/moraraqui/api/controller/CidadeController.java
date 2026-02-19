package com.moraraqui.api.controller;

import com.moraraqui.api.dto.CidadeRequestDTO;
import com.moraraqui.api.dto.CidadeResponseDTO;
import com.moraraqui.api.dto.EstadoRequestDTO;
import com.moraraqui.api.model.Estado;
import com.moraraqui.api.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @PostMapping
    public ResponseEntity<CidadeResponseDTO> criar(@Valid @RequestBody CidadeRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/estado/{estadoId}")
    public List<CidadeResponseDTO> listarPorEstado(@PathVariable Long estadoId) {
        return service.listarPorEstado(estadoId);
    }
}