package com.academico.models.auditoria;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIO_HISTORY")
public class UsuarioHistory {

	private Long idUsuarioHistory;
	private Long idUsuario;
	private Long idUsuarioLogado;
	private String operacao;
	private LocalDateTime date;

	public UsuarioHistory() {
		super();
	}

	public UsuarioHistory(Long idUsuarioHistory, Long idUsuario, Long idUsuarioLogado, String operacao,
			LocalDateTime date) {
		this.idUsuarioHistory = idUsuarioHistory;
		this.idUsuario = idUsuario;
		this.idUsuarioLogado = idUsuarioLogado;
		this.operacao = operacao;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_HIST_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCIA_USUARIO_HISTORY",initialValue = 1, allocationSize = 1, name = "USUARIO_HIST_SEQ")
	@Column(name="ID_USUARIO_HISTORY")
	public Long getIdUsuarioHistory() {
		return idUsuarioHistory;
	}

	public void setIdUsuarioHistory(Long idUsuarioHistory) {
		this.idUsuarioHistory = idUsuarioHistory;
	}

	@Column(name="ID_USUARIO")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name="ID_USUARIO_LOGADO")
	public Long getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(Long idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}

	@Column(name="OPERACAO")
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	@Column(name="DATA")
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuarioHistory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioHistory other = (UsuarioHistory) obj;
		return Objects.equals(idUsuarioHistory, other.idUsuarioHistory);
	}

}
