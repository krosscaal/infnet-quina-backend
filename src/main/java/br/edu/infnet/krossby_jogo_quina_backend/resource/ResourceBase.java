/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.resource;

import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class ResourceBase<T, ID> {
    protected ResourceBase() {}

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<T> incluir(@Valid @RequestBody T dto) throws BusinessException {
        return this.acaoIncluir(dto);
    }

    protected abstract ResponseEntity<T> acaoIncluir(T dto) throws BusinessException;

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<T> obter(@PathVariable("id") ID id) throws BusinessException {
        return this.acaoObterPorId(id);
    }

    protected abstract ResponseEntity<T> acaoObterPorId(ID id) throws BusinessException;


    @PutMapping(value = "/alterar/{id}", produces = {"application/json"})
    public ResponseEntity<T> alterar(@PathVariable("id") ID id, @Valid @RequestBody T dto) throws BusinessException {
        return this.acaoAlterar(id, dto);
    }

    protected abstract ResponseEntity<T> acaoAlterar(ID id, T dto) throws BusinessException;

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") ID id) throws BusinessException {
        this.acaoExcluir(id);
    }

    protected abstract void acaoExcluir(ID id) throws BusinessException;

}
