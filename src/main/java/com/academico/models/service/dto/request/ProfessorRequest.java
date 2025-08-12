package com.academico.models.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfessorRequest {

	
	private Long    idProfessor;
	private String  codProfessor;
	private String  nomeProfessor; 
	private Long    idUsuario;
	
	public ProfessorRequest() {
		super();
	}

	public ProfessorRequest(Long idProfessor, String codProfessor, String nomeProfessor, Long idUsuario) {
		super();
		this.idProfessor = idProfessor;
		this.codProfessor = codProfessor;
		this.nomeProfessor = nomeProfessor;
		this.idUsuario = idUsuario; 
	}	
	
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	
	@NotBlank(message = "O código do professor é obrigatório.")
	@Size(min = 5, max = 20, message = "O código do professor deve ter entre 5 e 20 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "O código do professor deve conter apenas letras e números.")
	public String getCodProfessor() {
		return codProfessor;
	}
	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}
	
	@NotBlank(message = "O nome do professor é obrigatório.")
	@Size(min = 5, max = 100, message = "O nome do professor deve ter entre 5 e 100 caracteres.")
	@Pattern(regexp = "^[A-Za]+$", message = "O nome do professor deve conter apenas letras ")
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
	
	
	
	
}


