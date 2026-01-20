/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_jogadores")
@AllArgsConstructor
public class Jogador extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 15)
    private String phone;
    @Column(nullable = false, length = 200)
    private String aposta;
}
