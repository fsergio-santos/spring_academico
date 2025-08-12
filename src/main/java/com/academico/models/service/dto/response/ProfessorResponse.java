package com.academico.models.service.dto.response;

public class ProfessorResponse {

	
	private Long    idProfessor;
	private String  codProfessor;
	private String  nomeProfessor; 
	private Long    idUsuario;
	private String  nomeUsuario;
	
	public ProfessorResponse() {
		super();
	}

	public ProfessorResponse(Long idProfessor, String codProfessor, String nomeProfessor, Long idUsuario, String nomeUsuario) {
		super();
		this.idProfessor = idProfessor;
		this.codProfessor = codProfessor;
		this.nomeProfessor = nomeProfessor;
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
	}
	
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public String getCodProfessor() {
		return codProfessor;
	}
	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
	
}


