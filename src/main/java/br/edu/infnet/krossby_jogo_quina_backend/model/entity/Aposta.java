/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.entity;

import br.edu.infnet.krossby_jogo_quina_backend.model.enumerator.TipoJogo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;
@Builder
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_aposta")
@AllArgsConstructor
public class Aposta extends EntidadeBase{

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "usuario_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_quina_usuario"))
    private Usuario usuario;

    @Column(nullable = false, length = 10)
    private String numeroAposta;

    @Column(nullable = false, length = 15)
    private String numeroJogo;

    @Column(nullable = false)
    private LocalDate dataJogo;

    @Enumerated(EnumType.STRING)
    private TipoJogo tipoJogo;
}
