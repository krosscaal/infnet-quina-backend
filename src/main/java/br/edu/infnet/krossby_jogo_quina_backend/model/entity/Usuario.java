/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.entity;

import br.edu.infnet.krossby_jogo_quina_backend.model.enumerator.TipoRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntidadeBase implements UserDetails {
    @Serial
    private static final long serialVersionUID = 5405172041950251807L;
    private static final String ROLE_PREFIX = "ROLE_";

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String userLogin;

    @Column(nullable = false, length = 100)
    private String userSenha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == TipoRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority(ROLE_PREFIX.concat(TipoRole.ADMIN.name())),
                    new SimpleGrantedAuthority(ROLE_PREFIX.concat(TipoRole.USER.name())));
        }
        return List.of(new SimpleGrantedAuthority(ROLE_PREFIX.concat(TipoRole.USER.name())));
    }

    @Override
    public String getPassword() {
        return userSenha;
    }

    @Override
    public String getUsername() {
        return userLogin;
    }
}
