package com.moraraqui.api.service;

import com.moraraqui.api.dto.DadosAtualizacaoImovel;
import com.moraraqui.api.dto.ImovelRequestDTO;
import com.moraraqui.api.dto.ImovelResponseDTO;
import com.moraraqui.api.model.*;
import com.moraraqui.api.model.enums.FinalidadeImovel;
import com.moraraqui.api.model.enums.TipoImovel;
import com.moraraqui.api.repository.*;
import com.moraraqui.api.repository.specs.ImovelSpecs;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public ImovelResponseDTO salvar(ImovelRequestDTO dto, Usuario anunciante) {

        Bairro bairro = bairroRepository.findById(dto.bairroId())
                .orElseThrow(() -> new EntityNotFoundException("Bairro não encontrado"));

        Cidade cidade = cidadeRepository.findById(dto.cidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));

        Imovel imovel = new Imovel(dto);
        imovel.setCidade(cidade);
        imovel.setAnunciante(anunciante);
        imovel.setBairro(bairro);

        Imovel salvo = imovelRepository.save(imovel);
        return new ImovelResponseDTO(salvo);
    }

    public Page<ImovelResponseDTO> listarTodos(Pageable pageable) {
        return imovelRepository.findAll(pageable)
                .map(ImovelResponseDTO::new);
    }

    public Page<ImovelResponseDTO> listarMeusImoveis(Long id, Pageable paginacao) {
        return imovelRepository.findAllByAnuncianteId(id, paginacao)
                .map(ImovelResponseDTO::new);
    }

    public List<ImovelResponseDTO> buscarComTodosFiltros(Long cidadeId, Long bairroId, BigDecimal precoMax, TipoImovel tipo, FinalidadeImovel finalidade, Integer quartos) {
        Specification<Imovel> spec = Specification
                .where(ImovelSpecs.porCidade(cidadeId))
                .and(ImovelSpecs.porBairro(bairroId))
                .and(ImovelSpecs.precoAte(precoMax))
                .and(ImovelSpecs.porTipo(tipo))
                .and(ImovelSpecs.porFinalidade(finalidade))
                .and(ImovelSpecs.minimoQuartos(quartos));

        return imovelRepository.findAll(spec).stream()
                .map(ImovelResponseDTO::new)
                .toList();
    }

    public ImovelResponseDTO atualizar(Long id, DadosAtualizacaoImovel dados, Usuario logado) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imóvel não encontrado"));

        validarDonoDoImovel(imovel, logado);

        imovel.atualizarInformacoes(dados);

        return new ImovelResponseDTO(imovel);
    }

    public void excluir(Long id, Usuario logado) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imóvel não encontrado"));

        validarDonoDoImovel(imovel, logado);

        imovel.setAtivo(false);
        imovelRepository.delete(imovel);
    }

    private void validarDonoDoImovel(Imovel imovel, Usuario logado) {
        if (!imovel.getAnunciante().getId().equals(logado.getId())) {
            throw new RuntimeException("Você não tem permissão para alterar este imóvel!");
        }
    }
}
