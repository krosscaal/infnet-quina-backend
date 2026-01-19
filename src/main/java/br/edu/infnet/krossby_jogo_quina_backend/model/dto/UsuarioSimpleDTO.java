/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.dto;

import java.util.UUID;
public record UsuarioSimpleDTO(UUID id, String nome, String email, String role){
}