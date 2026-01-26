/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.config.security.resource;

import br.edu.infnet.krossby_jogo_quina_backend.config.security.service.AuthService;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.LoginDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login (@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping(value = "/register", produces = {"application/json"})
    public ResponseEntity<Object> register(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(authService.register(usuarioDTO), HttpStatus.CREATED);
    }

}
