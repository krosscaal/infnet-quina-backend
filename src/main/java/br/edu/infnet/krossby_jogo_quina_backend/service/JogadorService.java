/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.service;

import br.edu.infnet.krossby_jogo_quina_backend.config.clients.ApiLoteriaCaixaQuinaFeignClient;
import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import br.edu.infnet.krossby_jogo_quina_backend.exception.NaoEncontradoException;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.JogadorDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.JogadorResponseDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.entity.Jogador;
import br.edu.infnet.krossby_jogo_quina_backend.repository.JogadorRepository;
import br.edu.infnet.krossby_jogo_quina_backend.util.GeralUtils;
import feign.FeignException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.edu.infnet.krossby_jogo_quina_backend.util.CentroDeMensagens.*;

@Service
public class JogadorService implements ServiceBase<JogadorDTO, UUID> {
    Logger logger = Logger.getLogger(JogadorService.class.getName());
    private final JogadorRepository jogadorRepository;
    private final ApiLoteriaCaixaQuinaFeignClient apiLoteriaCaixaQuinaFeignClient;

    public JogadorService(JogadorRepository jogadorRepository, ApiLoteriaCaixaQuinaFeignClient apiLoteriaCaixaQuinaFeignClient) {
        this.jogadorRepository = jogadorRepository;
        this.apiLoteriaCaixaQuinaFeignClient = apiLoteriaCaixaQuinaFeignClient;
    }

    @Override
    public JogadorDTO salvar(JogadorDTO objeto) {
        Jogador jogador = this.dtoToEntidade(objeto);
        return entidadeToDto(jogadorRepository.save(jogador));
    }

    private Jogador dtoToEntidade(JogadorDTO objeto) {
        this.validarJogador(objeto);
        Jogador jogador = Jogador.builder().build();
        BeanUtils.copyProperties(objeto, jogador,"id", "aposta");
        jogador.setAposta(this.tratarAposta(objeto.aposta()));
        return jogador;
    }
    private JogadorDTO entidadeToDto(Jogador entidade) {
        List<String> aposta = Arrays.stream(entidade.getAposta().split(",")).toList();
        return new JogadorDTO(
                entidade.getId(),
                entidade.getNome(),
                entidade.getEmail(),
                entidade.getPhone(),
                aposta);
    }

    private void validarJogador(JogadorDTO objeto) {
        if (objeto.aposta() == null || objeto.aposta().isEmpty()) {
            throw new BusinessException("campo aposta: ".concat(APOSTA_PRECISA_SER_INFORMADA));
        }
        if (objeto.aposta().size() != 5) {
            throw new BusinessException("campo aposta:".concat(FALTA_NUMEROS_EM_APOSTA));
        }
    }

    private String tratarAposta(List<String> aposta) {
        aposta.forEach(elemento -> {
            if (GeralUtils.contemNumeros(elemento)) {
                throw new BusinessException("campo aposta: ".concat(DEVE_CONTER_SOMENTE_NUMEROS));
            }
        });
        return GeralUtils.ordenarArrayListaToString(aposta);
    }

    @Override
    public JogadorDTO buscarPorId(UUID uuid) {
        Jogador jogador = this.buscarUsuarioPorId(uuid);
        return entidadeToDto(jogador);
    }

    @Override
    public JogadorDTO alterar(UUID idObjeto, JogadorDTO objeto) {
        Jogador jogador = this.buscarUsuarioPorId(idObjeto);
        BeanUtils.copyProperties(objeto, jogador, "id", "aposta");
        jogador.setAposta(this.tratarAposta(objeto.aposta()));
        return entidadeToDto(jogadorRepository.save(jogador));
    }

    @Override
    public void remover(UUID uuid) {
        this.buscarUsuarioPorId(uuid);
        jogadorRepository.deleteById(uuid);
    }

    public List<JogadorResponseDTO> buscarTodos() {
        List<Jogador> jogadores = jogadorRepository.findAll();
        List<JogadorResponseDTO> response = new ArrayList<>();
        jogadores.forEach(jogador -> {
            String[] numeros = jogador.getAposta().split(",");

            JogadorResponseDTO jogadorResponseDTO = new JogadorResponseDTO(
                    jogador.getId(),
                    jogador.getNome(),
                    jogador.getEmail(),
                    jogador.getPhone(),
                    numeros[0], numeros[1], numeros[2], numeros[3], numeros[4]);
            response.add(jogadorResponseDTO);
        });
        return response;
    }
    public Jogador buscarUsuarioPorId(UUID uuid) {
        return this.jogadorRepository.findById(uuid).orElseThrow(() -> new NaoEncontradoException(String.format("Não existe registro de jogador com o id: %s", uuid)));
    }


    public Page<JogadorResponseDTO> bucar(Integer page, Integer size) {

        int pagina = page == null ? 0 : Math.max(0, page);
        int tamanho = size == null ? 10 : Math.max(1, size);
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Jogador> jogadorPage = jogadorRepository.findAll(pageable);
        return jogadorPage.map(jogador -> {
            String[] numeros = jogador.getAposta().split(",");
            return new JogadorResponseDTO(
                    jogador.getId(),
                    jogador.getNome(),
                    jogador.getEmail(),
                    jogador.getPhone(),
                    numeros[0], numeros[1], numeros[2], numeros[3], numeros[4]);
        });
    }

}
