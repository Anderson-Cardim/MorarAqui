package com.moraraqui.api.controller;

import com.moraraqui.api.dto.DadosAutenticacao;
import com.moraraqui.api.dto.DadosTokenJWT;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.repository.UsuarioRepository;
import com.moraraqui.api.service.TokenService;
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

