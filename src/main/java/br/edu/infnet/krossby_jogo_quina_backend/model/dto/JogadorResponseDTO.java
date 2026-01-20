/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.dto;

import java.util.UUID;

public record JogadorResponseDTO(
        UUID id,
        String nome,
        String email,
        String phone,
        String num1,
        String num2,
        String num3,
        String num4,
        String num5
        ) {
}
