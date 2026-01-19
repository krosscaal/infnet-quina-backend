/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.resource;

import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioDTO;
import br.edu.infnet.krossby_jogo_quina_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/{uuid}", produces = {"application/json"})
    protected ResponseEntity<UsuarioDTO> acaoObterPorId(@PathVariable("uuid") UUID uuid) throws BusinessException {
        return ResponseEntity.ok(usuarioService.buscarPorId(uuid));
    }

    @PutMapping(value = "/alterar/{uuid}", produces = {"application/json"})
    protected ResponseEntity<UsuarioDTO> acaoAlterar(@PathVariable("uuid") UUID uuid, @RequestBody UsuarioDTO dto) throws BusinessException {
        return ResponseEntity.ok(usuarioService.alterar(uuid, dto));
    }

    @DeleteMapping(value = "/{uuid}")
    protected void acaoExcluir(@PathVariable("uuid") UUID uuid) throws BusinessException {
        usuarioService.remover(uuid);
    }
}
