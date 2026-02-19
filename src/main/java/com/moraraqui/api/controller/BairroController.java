package com.moraraqui.api.controller;

import com.moraraqui.api.dto.BairroRequestDTO;
import com.moraraqui.api.dto.BairroResponseDTO;
import com.moraraqui.api.dto.CidadeRequestDTO;
import com.moraraqui.api.dto.CidadeResponseDTO;
import com.moraraqui.api.service.BairroService;
import com.moraraqui.api.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairros")
public class BairroController {

    @Autowired
    private BairroService service;

    @PostMapping
    public ResponseEntity<BairroResponseDTO> criar(@Valid @RequestBody BairroRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/cidade/{cidadeId}")
    public List<BairroResponseDTO> listarPorEstado(@PathVariable Long cidadeId) {
        return service.listarPorCidade(cidadeId);
    }
}