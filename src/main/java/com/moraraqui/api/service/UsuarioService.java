package com.moraraqui.api.service;

import com.moraraqui.api.dto.UsuarioRequestDTO;
import com.moraraqui.api.dto.UsuarioResponseDTO;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new RuntimeException("Este e-mail já está cadastrado!");
        }

        Usuario usuario = new Usuario(dto);

        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        usuario.setSenha(senhaCriptografada);

        Usuario salvo = repository.save(usuario);
        return new UsuarioResponseDTO(salvo);
    }

}