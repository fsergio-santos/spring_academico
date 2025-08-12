package com.academico.models.service.listener;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.academico.models.auditoria.AlunoHistory;
import com.academico.models.enums.OperationsName;
import com.academico.models.model.Aluno;
import com.academico.models.repository.AlunoHistoryRepository;
import com.academico.models.service.security.UsuarioSistema;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class AlunoServiceListener {
	
	@Autowired
	private AlunoHistoryRepository alunoHistoryRepository;
	
    @PrePersist
	private void prePersist(Aluno Aluno) {
    	AlunoHistory AlunoHistory = setAlunoHistory(Aluno, OperationsName.INSERT.getNome());
    	alunoHistoryRepository.save(AlunoHistory);
	}
    
    @PreUpdate
    private void preUpdate(Aluno Aluno) {
    	AlunoHistory AlunoHistory = setAlunoHistory(Aluno, OperationsName.UPDATE.getNome() );
    	alunoHistoryRepository.save(AlunoHistory);
    }
    
    

	@PreRemove
    private void preRemove(Aluno Aluno) {
		AlunoHistory AlunoHistory = setAlunoHistory(Aluno, OperationsName.DELETE.getNome() );
    	alunoHistoryRepository.save(AlunoHistory);
    }
	
	private AlunoHistory setAlunoHistory(Aluno Aluno, String operacao) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioSistema usuarioSistema = (UsuarioSistema) auth.getPrincipal();
		
		AlunoHistory AlunoHistory = new AlunoHistory();
		
		AlunoHistory.setIdAluno(Aluno.getIdAluno());
		AlunoHistory.setIdUsuarioLogado(usuarioSistema.getId());
		AlunoHistory.setOperacao(operacao);
		AlunoHistory.setDate(LocalDateTime.now());
		
		return AlunoHistory;
	}
}
