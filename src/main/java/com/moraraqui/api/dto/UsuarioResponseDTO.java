package com.moraraqui.api.dto;

import com.moraraqui.api.model.Usuario;
import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String whatsapp,
        LocalDateTime criadoEm
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getWhatsapp(),
                usuario.getCriadoEm()
        );
    }
}
