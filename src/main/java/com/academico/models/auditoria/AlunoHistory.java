package com.academico.models.auditoria;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="ALUNO_HISTORY")
public class AlunoHistory {

	private Long idAlunoHistory;
	private Long idAluno;
	private Long idUsuarioLogado;
	private String operacao;
	private LocalDateTime date;

	public AlunoHistory() {
		super();
	}

	public AlunoHistory(Long idAlunoHistory, Long idAluno, Long idUsuarioLogado, String operacao, LocalDateTime date) {
		super();
		this.idAlunoHistory = idAlunoHistory;
		this.idAluno = idAluno;
		this.idUsuarioLogado = idUsuarioLogado;
		this.operacao = operacao;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUNO_HIST_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCIA_ALUNO_HISTORY",initialValue = 1, allocationSize = 1, name = "ALUNO_HIST_SEQ")
	@Column(name="ID_ALUNO_HISTORY")
	public Long getIdAlunoHistory() {
		return idAlunoHistory;
	}

	public void setIdAlunoHistory(Long idAlunoHistory) {
		this.idAlunoHistory = idAlunoHistory;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(Long idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
