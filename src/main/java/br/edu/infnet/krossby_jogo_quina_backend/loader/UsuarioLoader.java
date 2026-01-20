/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.loader;

import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.enumerator.TipoRole;
import br.edu.infnet.krossby_jogo_quina_backend.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UsuarioLoader implements ApplicationRunner {
    private final UsuarioService usuarioService;
    Logger logger = Logger.getLogger(UsuarioLoader.class.getName());

    public UsuarioLoader(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String senha = new BCryptPasswordEncoder().encode("123");
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().nome("Usuario Test").email("usertest@gmail.com").userLogin("user").userSenha(senha).role(TipoRole.USER).build();
        UsuarioDTO user = usuarioService.salvar(usuarioDTO);
        logger.log(Level.INFO, "Usuario criado com sucesso! -> {0}", user.toString());

    }
}
