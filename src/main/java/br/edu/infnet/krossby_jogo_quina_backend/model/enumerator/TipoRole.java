/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.model.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String nomeRole;

    public static TipoRole valueOfRole(final int ordinal) {
        return TipoRole.values()[ordinal];
    }
//    public static TipoRole valueOfRole(final String nomeRole) {
//        return TipoRole.valueOf(nomeRole);
//    }
    public static TipoRole valueOfRole(String nomeRole) {
        for (TipoRole tipoRole : TipoRole.values()) {
            if (tipoRole.getNomeRole().equalsIgnoreCase(nomeRole)) {
                return tipoRole;
            }
        }
        throw new IllegalArgumentException("Tipo de Acesso não eoncontrado");
    }

    public static boolean exists(String nomeRole) {
        for (TipoRole tipoRole : TipoRole.values()) {
            if (tipoRole.name().equalsIgnoreCase(nomeRole)) {
                return true;
            }
        }
        return false;
    }
}
