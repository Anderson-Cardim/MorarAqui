package com.moraraqui.api.controller;

import com.moraraqui.api.config.SecurityConfig;
import com.moraraqui.api.dto.DadosAutenticacao;
import com.moraraqui.api.dto.DadosTokenJWT;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.repository.UsuarioRepository;
import com.moraraqui.api.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacao do Usuário", description = "Controlador para efetuar login do usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    @Operation(summary = "Login de Usuário", description = "Método para login de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "400", description = "Email não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var usuarioNoBanco = repository.findByEmail(dados.email());

        if (usuarioNoBanco != null) {
            boolean senhaBate = encoder.matches(dados.senha(), usuarioNoBanco.getPassword());
            System.out.println("A senha digitada bate com a do banco? " + senhaBate);
        } else {
            System.out.println("Usuário não encontrado no banco!");
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }


}

