/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.krossby_jogo_quina_backend.service;

import br.edu.infnet.krossby_jogo_quina_backend.exception.BusinessException;
import br.edu.infnet.krossby_jogo_quina_backend.exception.NaoEncontradoException;
import br.edu.infnet.krossby_jogo_quina_backend.model.dto.UsuarioDTO;
import br.edu.infnet.krossby_jogo_quina_backend.model.entity.Usuario;
import br.edu.infnet.krossby_jogo_quina_backend.model.enumerator.TipoRole;
import br.edu.infnet.krossby_jogo_quina_backend.repository.UsuarioRepository;
import br.edu.infnet.krossby_jogo_quina_backend.util.GeralUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static br.edu.infnet.krossby_jogo_quina_backend.util.CentroDeMensagens.E_UM_CAMPO_OBRIGATORIO;
import static br.edu.infnet.krossby_jogo_quina_backend.util.CentroDeMensagens.FORMATO_E_MAIL_INCORRETO;

@Service
public class UsuarioService implements ServiceBase<UsuarioDTO, UUID> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Transactional
    @Override
    public UsuarioDTO salvar(UsuarioDTO objeto) {
        Usuario usuario = this.dtoToEntity(objeto);
        Usuario usuarioSaved = this.usuarioRepository.save(usuario);
        return this.entityToDto(usuarioSaved);
    }

    private UsuarioDTO entityToDto(Usuario usuarioSaved) {
        return UsuarioDTO.builder()
                .id(usuarioSaved.getId())
                .nome(usuarioSaved.getNome())
                .email(usuarioSaved.getEmail())
                .userLogin(usuarioSaved.getUserLogin())
                .role(usuarioSaved.getRole())
                .build();
    }

    private Usuario dtoToEntity(UsuarioDTO objeto) {
        this.verificarUsuario(objeto);
        Usuario usuario = Usuario.builder().build();
        BeanUtils.copyProperties(objeto, usuario, "id");
        return usuario;
    }

    private void verificarUsuario(UsuarioDTO objeto) {
        if (!GeralUtils.emailValido(objeto.getEmail())) {
            throw new BusinessException("campo ".concat(FORMATO_E_MAIL_INCORRETO));
        }
        if (objeto.getRole() == null)
            throw new BusinessException("campo role :".concat(E_UM_CAMPO_OBRIGATORIO));
        if (!TipoRole.exists(objeto.getRole().name()))
            throw new BusinessException("campo role : ".concat("não existe role com esse tipo"));
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO buscarPorId(UUID uuid) {
        Usuario usuario = this.buscarUsuarioPorId(uuid);

        return entityToDto(usuario);
    }

    @Transactional
    @Override
    public UsuarioDTO alterar(UUID idObjeto, UsuarioDTO objeto) {
        Usuario usuarioEntidade = this.buscarUsuarioPorId(idObjeto);
        BeanUtils.copyProperties(objeto, usuarioEntidade);
        return entityToDto(usuarioRepository.save(usuarioEntidade));
    }

    @Transactional
    @Override
    public void remover(UUID uuid) {
        this.buscarUsuarioPorId(uuid);
        this.usuarioRepository.deleteById(uuid);
    }

    public Usuario buscarUsuarioPorId(UUID id) {
        return this.usuarioRepository.findById(id).orElseThrow(()-> new NaoEncontradoException(String.format("Usuário com %s, não encontrado!", id)));
    }

}
