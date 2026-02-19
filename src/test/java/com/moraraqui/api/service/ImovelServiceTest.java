package com.moraraqui.api.service;

import com.moraraqui.api.dto.ImovelRequestDTO;
import com.moraraqui.api.dto.ImovelResponseDTO;
import com.moraraqui.api.model.Bairro;
import com.moraraqui.api.model.Cidade;
import com.moraraqui.api.model.Imovel;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.model.enums.FinalidadeImovel;
import com.moraraqui.api.model.enums.TipoImovel;
import com.moraraqui.api.repository.BairroRepository;
import com.moraraqui.api.repository.CidadeRepository;
import com.moraraqui.api.repository.ImovelRepository;
import com.moraraqui.api.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImovelServiceTest {

    @Mock
    private ImovelRepository imovelRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BairroRepository bairroRepository;

    @Mock
    private CidadeRepository cidadeRepository;

    @InjectMocks
    private ImovelService imovelService;

    @Test
    @DisplayName("Deve salvar um imóvel com sucesso quando dados forem válidos")
    void salvar1() {
        Long bairroId = 1L;
        Long cidadeId = 1L;

        Usuario anunciante = new Usuario();
        anunciante.setId(1L);

        ImovelRequestDTO dtoImovel = new ImovelRequestDTO("Titulo", "Descrição", new BigDecimal("1000"), 2.0, 2, 2, 2, "Ponto de referencia", anunciante.getId(), bairroId, cidadeId, TipoImovel.CASA, FinalidadeImovel.ALUGUEL) ;

        Bairro bairroMock = new Bairro();
        bairroMock.setId(bairroId);

        Cidade cidadeMock = new Cidade();
        cidadeMock.setId(cidadeId);

        when(bairroRepository.findById(bairroId)).thenReturn(Optional.of(bairroMock));
        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.of(cidadeMock));

        when(imovelRepository.save(any(Imovel.class))).thenAnswer(i -> i.getArguments()[0]);

        ImovelResponseDTO resposta = imovelService.salvar(dtoImovel, anunciante);

        assertNotNull(resposta);
        assertEquals(dtoImovel.titulo(), resposta.titulo());

        verify(bairroRepository).findById(bairroId);
        verify(cidadeRepository).findById(cidadeId);
        verify(imovelRepository, times(1)).save(any(Imovel.class));
    }

    @Test
    void salvar2() {

    }

    @Test
    void listarTodos() {
    }

    @Test
    void listarMeusImoveis() {
    }

    @Test
    void buscarComTodosFiltros() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void excluir() {
    }
}