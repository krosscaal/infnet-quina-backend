/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.resource;

import br.edu.infnet.krossby_jogo_quina_backend.config.clients.ApiLoteriaCaixaQuinaFeignClient;
import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.ApostaDTO;
import br.edu.infnet.krossby_jogo_quina_backend.service.ApostaQuinaService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/aposta")
public class ApostaQuinaResource extends ResourceBase<ApostaDTO, UUID>{

    private final ApostaQuinaService apostaQuinaService;
    private final ApiLoteriaCaixaQuinaFeignClient apiLoteriaCaixaQuinaFeignClient;

    public ApostaQuinaResource(ApostaQuinaService apostaQuinaService, ApiLoteriaCaixaQuinaFeignClient apiLoteriaCaixaQuinaFeignClient) {
        this.apostaQuinaService = apostaQuinaService;
        this.apiLoteriaCaixaQuinaFeignClient = apiLoteriaCaixaQuinaFeignClient;
    }

    @Override
    protected ResponseEntity<ApostaDTO> acaoIncluir(ApostaDTO dto) throws BusinessException {
        return new ResponseEntity<>(apostaQuinaService.salvar(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<ApostaDTO> acaoObterPorId(UUID uuid) throws BusinessException {
        return ResponseEntity.ok(apostaQuinaService.buscarPorId(uuid));
    }

    @Override
    protected ResponseEntity<ApostaDTO> acaoAlterar(UUID uuid, ApostaDTO dto) throws BusinessException {
        return ResponseEntity.ok(apostaQuinaService.alterar(uuid, dto));
    }

    @Override
    protected void acaoExcluir(UUID uuid) throws BusinessException {
        apostaQuinaService.remover(uuid);
    }
    @GetMapping(value = "/buscar", produces = {"application/json"})
    public Page<ApostaDTO> acaoBuscar(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return apostaQuinaService.buscar(page, size);
    }

    @GetMapping(value = "/quina")
    public ResponseEntity<ApiLoteriaCaixaQuinaFeignClient.ApiCaixaQuinaResponse> obterJogoQuina() {
        return ResponseEntity.ok(apiLoteriaCaixaQuinaFeignClient.obterLoteriaQuinaCaixa());
    }

    @GetMapping(value = "/quina/{jogo}")
    public ResponseEntity<ApiLoteriaCaixaQuinaFeignClient.ApiCaixaQuinaResponse> obterJogoQuina(@PathVariable("jogo") String jogo) {
        return ResponseEntity.ok(apiLoteriaCaixaQuinaFeignClient.obterLoteriaQuinaCaixa(jogo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/outro")
    public String outro() {
        return "Outro";
    }
}
