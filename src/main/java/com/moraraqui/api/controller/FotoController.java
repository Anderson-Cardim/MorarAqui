package com.moraraqui.api.controller;

import com.moraraqui.api.dto.*;
import com.moraraqui.api.model.Foto;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.service.DiscoService;
import com.moraraqui.api.service.FotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private FotoService service;

    @Autowired
    private DiscoService Discoservice;

    @PostMapping
    public ResponseEntity<List<FotoResponseDTO>> cadastrar(
            @Valid FotoRequestDTO dto,
            @RequestParam("arquivos") List<MultipartFile> arquivos,
            @AuthenticationPrincipal Usuario logado) {

        List<FotoResponseDTO> fotosSalvas = service.salvar(dto, arquivos, logado);
        return ResponseEntity.status(HttpStatus.CREATED).body(fotosSalvas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id, @AuthenticationPrincipal Usuario logado) throws IOException {
        Discoservice.excluirFoto(id, logado);
        return ResponseEntity.noContent().build();
    }


}