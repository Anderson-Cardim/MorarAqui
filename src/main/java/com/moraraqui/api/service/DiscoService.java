package com.moraraqui.api.service;

import com.moraraqui.api.model.Foto;
import com.moraraqui.api.model.Imovel;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.repository.FotoRepository;
import com.moraraqui.api.repository.ImovelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class DiscoService {

    @Value("${imoveis.fotos.diretorio}")
    private String diretorioFotos;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private FotoRepository fotoRepository;

    public String salvarFoto(MultipartFile arquivo) {
        String nomeLimpo = arquivo.getOriginalFilename().replaceAll("\\s+", "_");
        String nomeArquivo = UUID.randomUUID() + "_" + nomeLimpo;
        Path arquivoPath = Paths.get(this.diretorioFotos).resolve(nomeArquivo);

        try {
            Files.createDirectories(arquivoPath.getParent());
            arquivo.transferTo(arquivoPath.toFile());
            return nomeArquivo;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }
    }

    public void excluirFoto(Long id, Usuario logado) throws IOException {

        Foto foto = fotoRepository.findById(id).orElseThrow();

        validarDonoDaFoto(foto, logado);

        Path caminho = Paths.get(this.diretorioFotos).resolve(foto.getUrl());
        try {
            Files.deleteIfExists(caminho);
        } catch (IOException e) {
        }

        fotoRepository.delete(foto);
    }

    private void validarDonoDaFoto(Foto foto, Usuario logado) {
        if (!foto.getImovel().getAnunciante().getId().equals(logado.getId())) {
            throw new RuntimeException("Você não tem permissão para deletar esta foto!");
        }
    }
}
