package com.moraraqui.api.service;

import com.moraraqui.api.dto.FotoRequestDTO;
import com.moraraqui.api.dto.FotoResponseDTO;
import com.moraraqui.api.model.Foto;
import com.moraraqui.api.model.Imovel;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.repository.FotoRepository;
import com.moraraqui.api.repository.ImovelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private DiscoService discoService;

    public List<FotoResponseDTO> salvar(FotoRequestDTO dto, List<MultipartFile> arquivos, Usuario logado) {

        List<FotoResponseDTO> resultado = new ArrayList<>();

        Imovel imovel = imovelRepository.findById(dto.imovelId())
                .orElseThrow(() -> new EntityNotFoundException("Não é possível salvar a foto: Imóvel com ID " + dto.imovelId() + " não existe."));

        if (!imovel.getAnunciante().getId().equals(logado.getId())) {
            throw new RuntimeException("Acesso negado para este imóvel");
        }

        for (MultipartFile arquivo : arquivos) {
            String nomeArquivo = discoService.salvarFoto(arquivo);

            Foto foto = new Foto();
            foto.setUrl(nomeArquivo);
            foto.setImovel(imovel);
            foto.setOrdem(dto.ordem());

            fotoRepository.save(foto);
            resultado.add(new FotoResponseDTO(foto));
        }

        return resultado;
    }

}