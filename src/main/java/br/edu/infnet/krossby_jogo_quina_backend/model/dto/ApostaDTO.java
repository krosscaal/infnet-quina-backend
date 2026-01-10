/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.dto;

import br.edu.infnet.krossby_jogo_quina_backend.model.enumerator.TipoJogo;
import br.edu.infnet.krossby_jogo_quina_backend.model.entity.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ApostaDTO(UUID id, Usuario usuario, List<String> aposta, String numeroJogo, LocalDate dataJogo, TipoJogo tipoJogo) {
}
