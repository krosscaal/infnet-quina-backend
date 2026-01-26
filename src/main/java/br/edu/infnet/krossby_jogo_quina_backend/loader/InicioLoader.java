/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.loader;

import br.edu.infnet.krossby_jogo_quina_backend.model.dto.JogadorDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.enumerator.TipoRole;
import br.edu.infnet.krossby_jogo_quina_backend.service.JogadorService;
import br.edu.infnet.krossby_jogo_quina_backend.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class InicioLoader implements ApplicationRunner {
    private final UsuarioService usuarioService;
    private final JogadorService jogadorService;
    Logger logger = Logger.getLogger(InicioLoader.class.getName());

    public InicioLoader(UsuarioService usuarioService, JogadorService jogadorService) {
        this.usuarioService = usuarioService;
        this.jogadorService = jogadorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JogadorDTO jogadorDTO1 = new JogadorDTO(null,"Krossby Camacho", "krossby@gmail.com", "6199999999", Arrays.asList("01","20","13","44","25"));
        JogadorDTO jogadorDTO2 = new JogadorDTO(null,"Carlos Costa", "carlos@gmail.com", "41984793814", Arrays.asList("11","20","33","44","45"));
        JogadorDTO jogadorDTO3 = new JogadorDTO(null,"Vera Lucia", "veralucia.the@gmail.com", "41984842578", Arrays.asList("11","09","13","25","45"));
        JogadorDTO jogadorDTO4 = new JogadorDTO(null,"Maria Lopes", ",ariathe@gmail.com", "41984842580", Arrays.asList("11","09","33","25","45"));
        JogadorDTO jogadorDTO5 = new JogadorDTO(null,"Camila", "camila@gmail.com", "41984842590", Arrays.asList("11","45","13","25","45"));
        JogadorDTO jogadorDTO6 = new JogadorDTO(null,"Joaquin", "joaquin.the@gmail.com", "41974842578", Arrays.asList("10","09","27","56","45"));
        JogadorDTO jogadorDTO7 = new JogadorDTO(null,"Fabricio", "fabriciothe@gmail.com", "41995502578", Arrays.asList("11","09","43","22","45"));
        JogadorDTO jogadorDTO8 = new JogadorDTO(null,"Adelina", "adelinathe@gmail.com", "41986252578", Arrays.asList("11","03","53","60","45"));
        JogadorDTO jogadorDTO9 = new JogadorDTO(null,"Karla", "karla.the@gmail.com", "41984843030", Arrays.asList("15","09","13","25","45"));
        JogadorDTO jogadorDTO10 = new JogadorDTO(null,"Julio Cesar", "juliocesar.the@gmail.com", "41984842000", Arrays.asList("10","09","70","25","75"));
        jogadorService.salvar(jogadorDTO1);
        jogadorService.salvar(jogadorDTO2);
        jogadorService.salvar(jogadorDTO3);
        jogadorService.salvar(jogadorDTO4);
        jogadorService.salvar(jogadorDTO5);
        jogadorService.salvar(jogadorDTO6);
        jogadorService.salvar(jogadorDTO7);
        jogadorService.salvar(jogadorDTO8);
        jogadorService.salvar(jogadorDTO9);
        jogadorService.salvar(jogadorDTO10);
        logger.log(Level.INFO, "Jogadores criados com sucesso!");

        String senha = new BCryptPasswordEncoder().encode("123");
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().nome("Usuario Test").email("user@gmail.com").userLogin("user").userSenha(senha).role(TipoRole.USER).build();
        UsuarioDTO user = usuarioService.salvar(usuarioDTO);
        logger.log(Level.INFO, "Usuario criado com sucesso! -> {0}", user);


    }
}
