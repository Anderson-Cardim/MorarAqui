package com.moraraqui.api.repository;

import com.moraraqui.api.dto.UsuarioRequestDTO;
import com.moraraqui.api.model.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar UserDetails quando o email existir no banco")
    void findByEmail() {
        String email = "anderson10cardim@gmail.com";
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Anderson", email, "123456", "73998070124");
        this.criarUsuario(dto);

        UserDetails result = this.usuarioRepository.findByEmail(email);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(email);
    }

    @Test
    @DisplayName("Deve retornar null quando o email não existir no banco")
    void findByEmail02() {
        String email = "anderson10cardim@gmail.com";

        UserDetails result = this.usuarioRepository.findByEmail(email);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Deve retornar true quando existir um email cadastrado")
    void existsByEmail() {

        String email = "anderson10cardim@gmail.com";

        UsuarioRequestDTO dto = new UsuarioRequestDTO("Anderson", email, "123456", "73998070124");
        this.criarUsuario(dto);

        boolean exists = this.usuarioRepository.existsByEmail(email);

        assertThat(exists).isTrue();
    }

    private Usuario criarUsuario(UsuarioRequestDTO DTO) {
        Usuario newUser = new Usuario(DTO);
        this.entityManager.persist(newUser);
        return newUser;
    }
}