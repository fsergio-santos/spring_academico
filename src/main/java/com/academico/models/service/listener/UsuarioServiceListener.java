package com.academico.models.service.listener;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.academico.models.auditoria.UsuarioHistory;
import com.academico.models.enums.OperationsName;
import com.academico.models.model.Usuario;
import com.academico.models.repository.UsuarioHistoryRepository;
import com.academico.models.service.security.UsuarioSistema;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class UsuarioServiceListener {
	
	@Autowired
	private UsuarioHistoryRepository usuarioHistoryRepository;
	
    @PrePersist
	private void prePersist(Usuario usuario) {
    	UsuarioHistory usuarioHistory = setUsuarioHistory(usuario, OperationsName.INSERT.getNome());
    	usuarioHistoryRepository.save(usuarioHistory);
	}
    
    @PreUpdate
    private void preUpdate(Usuario usuario) {
    	UsuarioHistory usuarioHistory = setUsuarioHistory(usuario, OperationsName.UPDATE.getNome() );
    	usuarioHistoryRepository.save(usuarioHistory);
    }
    
    

	@PreRemove
    private void preRemove(Usuario usuario) {
		UsuarioHistory usuarioHistory = setUsuarioHistory(usuario, OperationsName.DELETE.getNome() );
    	usuarioHistoryRepository.save(usuarioHistory);
    }
	
	private UsuarioHistory setUsuarioHistory(Usuario usuario, String operacao) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioSistema usuarioSistema = (UsuarioSistema) auth.getPrincipal();
		
		UsuarioHistory usuarioHistory = new UsuarioHistory();
		
		usuarioHistory.setIdUsuario(usuario.getIdUsuario());
		usuarioHistory.setIdUsuarioLogado(usuarioSistema.getId());
		usuarioHistory.setOperacao(operacao);
		usuarioHistory.setDate(LocalDateTime.now());
		
		return usuarioHistory;
	}
}
