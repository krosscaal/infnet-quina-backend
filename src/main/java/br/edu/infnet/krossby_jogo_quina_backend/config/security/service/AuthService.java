/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.config.security.service;

import br.edu.infnet.krossby_jogo_quina_backend.config.security.TokenService;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.LoginDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.LoginResponseDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioSimpleDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.entity.Usuario;
import br.edu.infnet.krossby_jogo_quina_backend.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, UsuarioRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.userLogin(), loginDTO.userSenha());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return new LoginResponseDTO(token);
    }
    public UsuarioSimpleDTO register(UsuarioDTO usuarioDTO) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(usuarioDTO.getUserSenha());
        Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getUserLogin(), encryptedSenha, usuarioDTO.getRole());
        Usuario usuarioSaved = this.repository.save(novoUsuario);
        return new UsuarioSimpleDTO(usuarioSaved.getId(), usuarioSaved.getNome(), usuarioSaved.getEmail(), usuarioSaved.getUserLogin());
    }
}
