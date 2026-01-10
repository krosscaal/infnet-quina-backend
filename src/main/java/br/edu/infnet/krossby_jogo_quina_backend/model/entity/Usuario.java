/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;

@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntidadeBase {
    @Serial
    private static final long serialVersionUID = 5405172041950251807L;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String userLogin;

    @Column(nullable = false, length = 100)
    private String userSenha;



}
