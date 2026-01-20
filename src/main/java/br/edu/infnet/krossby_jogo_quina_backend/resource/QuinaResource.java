/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.resource;

import br.edu.infnet.krossby_jogo_quina_backend.config.clients.ApiLoteriaCaixaQuinaFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuinaResource {
    private final ApiLoteriaCaixaQuinaFeignClient apiLoteriaCaixaQuinaFeignClient;

    public QuinaResource(ApiLoteriaCaixaQuinaFeignClient apiLoteriaCaixaQuinaFeignClient) {
        this.apiLoteriaCaixaQuinaFeignClient = apiLoteriaCaixaQuinaFeignClient;
    }

    @GetMapping(value = "/quina")
    public ResponseEntity<ApiLoteriaCaixaQuinaFeignClient.ApiCaixaQuinaResponse> obterJogoQuina() {
        return ResponseEntity.ok(apiLoteriaCaixaQuinaFeignClient.obterLoteriaQuinaCaixa());
    }

    @GetMapping(value = "/quina/{jogo}")
    public ResponseEntity<ApiLoteriaCaixaQuinaFeignClient.ApiCaixaQuinaResponse> obterJogoQuina(@PathVariable("jogo") String jogo) {
        return ResponseEntity.ok(apiLoteriaCaixaQuinaFeignClient.obterLoteriaQuinaCaixa(jogo));
    }
}
