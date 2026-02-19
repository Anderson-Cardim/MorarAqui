package com.moraraqui.api.service;

import com.moraraqui.api.dto.BairroRequestDTO;
import com.moraraqui.api.dto.BairroResponseDTO;
import com.moraraqui.api.model.Bairro;
import com.moraraqui.api.model.Cidade;
import com.moraraqui.api.repository.BairroRepository;
import com.moraraqui.api.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public BairroResponseDTO salvar(BairroRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(dto.cidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Não é possível encontrar a cidade: Cidade com ID " + dto.cidadeId() + " não existe."));

        Bairro bairro = new Bairro(dto, cidade);
        bairro.setNome(dto.nome());
        bairro.setCidade(cidade);

        return new BairroResponseDTO(bairroRepository.save(bairro));
    }

    public List<BairroResponseDTO> listarPorCidade(Long cidadeId) {
        return bairroRepository.findByCidadeId(cidadeId).stream()
                .map(BairroResponseDTO::new)
                .toList();
    }

}