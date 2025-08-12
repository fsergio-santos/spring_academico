package com.academico.models.service.dto.response;

import java.math.BigDecimal;

import com.academico.models.model.Professor;
import com.fasterxml.jackson.annotation.JsonInclude;

public class DisciplinaResponse {
	
	private Long idDisciplina;
	private String codDisciplina;
	private String nomeDisciplina;
	private Professor professor;
	private Long idProfessor;
	private String nome_professor; 
	private Integer rowNum;
	
	
	public DisciplinaResponse(
			Long idDisciplina, 
			String codDisciplina, 
			String nomeDisciplina, 
			Long idProfessor,
			String nome_professor 
			) {
		super();
		this.idDisciplina = idDisciplina;
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.idProfessor = idProfessor;
		this.nome_professor = nome_professor;

	}
	
	public DisciplinaResponse(
			Long idDisciplina, 
			String codDisciplina, 
			String nomeDisciplina, 
			String nome_professor 
			) {
		super();
		this.idDisciplina = idDisciplina;
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.nome_professor = nome_professor;

	}
	
	
	public DisciplinaResponse (
			BigDecimal id,
	    	String codDisciplina, 
			String nomeDisciplina, 
			BigDecimal idProfessor,
			String nome_professor,
			BigDecimal rowNum ) { 
			
		    this.idDisciplina = id.longValue();
		    this.codDisciplina = codDisciplina;
			this.nomeDisciplina = nomeDisciplina;
			this.idProfessor = idProfessor.longValue();
			this.nome_professor = nome_professor;
			
	}


	public Long getIdDisciplina() {
		return idDisciplina;
	}


	public String getCodDisciplina() {
		return codDisciplina;
	}


	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL) 
	public Professor getProfessor() {
		return professor;
	}


	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Long getIdProfessor() {
		return idProfessor;
	}


	public String getNome_professor() {
		return nome_professor;
	}


	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getRowNum() {
		return rowNum;
	}
	
	
	
	

}
