/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.exceptionhandler;

import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import br.edu.infnet.krossby_jogo_quina_backend.exception.NaoEncontradoException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CAMPOS_INVALIDOS = "Um o mais campos estão inválidos!";
    private static final String FORMATO_DATA = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern(FORMATO_DATA);
    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Campo> campos = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> campos.add(new Campo(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale()))));
        ApiErrors apiErrors = new ApiErrors(status.value(), LocalDateTime.now().format(dataFormatada), CAMPOS_INVALIDOS, campos);
        return handleExceptionInternal(ex, apiErrors, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrors apiErrors = new ApiErrors(status.value(), LocalDateTime.now().format(dataFormatada), ex.getMessage());
        return handleExceptionInternal(ex, apiErrors, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrors apiErrors = new ApiErrors(status.value(), LocalDateTime.now().format(dataFormatada), ex.getMessage());
        return handleExceptionInternal(ex, apiErrors, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Object> handleNaoEncontradoException(NaoEncontradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrors apiErrors = new ApiErrors(status.value(), LocalDateTime.now().format(dataFormatada), ex.getMessage());
        return handleExceptionInternal(ex, apiErrors, new HttpHeaders(), status, request);
    }
}
