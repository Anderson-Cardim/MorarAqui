package com.moraraqui.api.controller;

import com.moraraqui.api.dto.EstadoRequestDTO;
import com.moraraqui.api.dto.EstadoResponseDTO;
import com.moraraqui.api.model.Estado;
import com.moraraqui.api.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadoResponseDTO>> listarEstados() {
        List<EstadoResponseDTO> estados;
        estados = service.listarTodos();
        return ResponseEntity.ok(estados);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Estado> cadastrarEstado(@RequestBody @Valid EstadoRequestDTO estadoDTO) {
        Estado estado = service.salvar(estadoDTO);
        return new ResponseEntity<>(estado, HttpStatus.CREATED);
    }

}
