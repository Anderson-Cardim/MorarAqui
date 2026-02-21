package com.moraraqui.api.controller;

import com.moraraqui.api.config.SecurityConfig;
import com.moraraqui.api.dto.UsuarioRequestDTO;
import com.moraraqui.api.dto.UsuarioResponseDTO;
import com.moraraqui.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "usuario", description = "Controlador para cadastrar usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    @Operation(summary = "Salva dados de Usuário", description = "Método para salvar/cadastrar dados de usuário")
    @ApiResponse(responseCode = "201", description = "Usuário gravado com sucesso")
    @ApiResponse(responseCode = "400", description = "Email já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }
}
