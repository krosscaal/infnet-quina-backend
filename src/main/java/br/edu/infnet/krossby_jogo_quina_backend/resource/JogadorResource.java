/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.resource;

import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.JogadorDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.JogadorResponseDTO;
import br.edu.infnet.krossby_jogo_quina_backend.service.JogadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jogador")
public class JogadorResource extends ResourceBase<JogadorDTO, UUID>{
    private final JogadorService jogadorService;

    public JogadorResource(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @Override
    protected ResponseEntity<JogadorDTO> acaoIncluir(JogadorDTO dto) throws BusinessException {
        return new ResponseEntity<>(jogadorService.salvar(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<JogadorDTO> acaoObterPorId(UUID uuid) throws BusinessException {
        return ResponseEntity.ok(jogadorService.buscarPorId(uuid));
    }

    @Override
    protected ResponseEntity<JogadorDTO> acaoAlterar(UUID uuid, JogadorDTO dto) throws BusinessException {
        return ResponseEntity.ok(jogadorService.alterar(uuid, dto));
    }

    @Override
    protected void acaoExcluir(UUID uuid) throws BusinessException {
        jogadorService.remover(uuid);
    }
    @GetMapping(value = "/todos", produces = {"application/json"})
    public ResponseEntity<List<JogadorResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(jogadorService.buscarTodos());
    }
}
