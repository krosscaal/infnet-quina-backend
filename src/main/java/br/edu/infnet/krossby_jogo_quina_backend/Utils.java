/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend;

import org.springframework.util.StringUtils;

import java.util.List;

public class Utils {
    private Utils() {
    }
    public static String ordenarArrayListaToString(List<String> lista) {
        lista.sort(null);
        return StringUtils.collectionToCommaDelimitedString(lista);

    }
}
