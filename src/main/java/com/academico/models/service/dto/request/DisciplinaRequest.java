package com.academico.models.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DisciplinaRequest {
	
	private Long idDisciplina;
	private String codDisciplina;
	private String nomeDisciplina;
    private Long idProfessor;
	
	
	
	public DisciplinaRequest() {
		super();
	}
	
	
	public DisciplinaRequest(Long idDisciplina, String codDisciplina, String nomeDisciplina, Long idProfessor) {
		super();
		this.idDisciplina = idDisciplina;
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.idProfessor = idProfessor;
	}


	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	@NotBlank(message = "O código da disciplina é obrigatório.")
	@Size(min = 5, max = 20, message = "O código da disciplina deve ter entre 5 e 20 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "O código da disciplina deve conter apenas letras e números.")
	public String getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
	
	@NotBlank(message = "O nome da disciplina é obrigatório.")
	@Size(min = 5, max = 100, message = "O nome do disciplina deve ter entre 5 e 100 caracteres.")
	@Pattern(regexp = "^[A-Za]+$", message = "O nome da disciplina deve conter apenas letras ")
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}


	@NotBlank(message = "O código do professor é obrigatório.")
	@Pattern(regexp = "^[0-9]+$", message = "O código do professor deve conter apenas números ")
	public Long getIdProfessor() {
		return idProfessor;
	}


	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	
	

}
