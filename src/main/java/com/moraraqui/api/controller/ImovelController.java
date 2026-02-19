package com.moraraqui.api.controller;

import com.moraraqui.api.dto.DadosAtualizacaoImovel;
import com.moraraqui.api.dto.ImovelRequestDTO;
import com.moraraqui.api.dto.ImovelResponseDTO;
import com.moraraqui.api.model.Imovel;
import com.moraraqui.api.model.Usuario;
import com.moraraqui.api.model.enums.FinalidadeImovel;
import com.moraraqui.api.model.enums.TipoImovel;
import com.moraraqui.api.service.ImovelService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<ImovelResponseDTO> criar(
            @Valid @RequestBody ImovelRequestDTO dto,
            @AuthenticationPrincipal Usuario logado) {

        Imovel imovel = new Imovel(dto);

        var response = service.salvar(dto, logado);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ImovelResponseDTO>> listar(@PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ImovelResponseDTO>> buscar(
            @RequestParam(required = false) Long cidadeId,
            @RequestParam(required = false) Long bairroId,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(required = false) TipoImovel tipo,
            @RequestParam(required = false) FinalidadeImovel finalidade,
            @RequestParam(required = false) Integer quartos
    ) {
        return ResponseEntity.ok(service.buscarComTodosFiltros(cidadeId, bairroId, precoMax, tipo, finalidade, quartos));
    }

    @GetMapping("/meus")
    public ResponseEntity<Page<ImovelResponseDTO>> listarMeusImoveis(
            @AuthenticationPrincipal Usuario logado,
            @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {

        var pagina = service.listarMeusImoveis(logado.getId(), paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoImovel dados, @AuthenticationPrincipal Usuario logado) {
        var response = service.atualizar(id, dados, logado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id, @AuthenticationPrincipal Usuario logado) {
        service.excluir(id, logado);
        return ResponseEntity.noContent().build();
    }

}