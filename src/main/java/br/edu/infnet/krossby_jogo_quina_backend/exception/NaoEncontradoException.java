/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.exception;

import java.io.Serial;

public class NaoEncontradoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
