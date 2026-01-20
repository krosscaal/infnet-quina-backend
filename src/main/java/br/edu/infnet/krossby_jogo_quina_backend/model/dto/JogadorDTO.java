/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import static br.edu.infnet.krossby_jogo_quina_backend.util.CentroDeMensagens.*;

public record JogadorDTO(
        UUID id,
        @NotBlank(message = NAO_VAZIO)
        String nome,
        @NotBlank(message = NAO_VAZIO)
        @Email(message = FORMATO_E_MAIL_INCORRETO)
        String email,
        @NotBlank(message = NAO_VAZIO)
        String phone,
        @NotNull(message = NAO_NULL)
        List<String> aposta) {
}
