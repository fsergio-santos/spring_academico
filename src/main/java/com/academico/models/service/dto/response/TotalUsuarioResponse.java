package com.academico.models.service.dto.response;

public class TotalUsuarioResponse {
	
	private Integer totalUsuarios;
	private Integer totalAlunos;
	private Integer totalProfessores;
	
	public TotalUsuarioResponse() {

	}

	public TotalUsuarioResponse(Integer totalUsuarios, Integer totalAlunos, Integer totalProfessores) {
	    this.totalUsuarios = totalUsuarios;
		this.totalAlunos = totalAlunos;
		this.totalProfessores = totalProfessores;
	}

	public Integer getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(Integer totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}

	public Integer getTotalAlunos() {
		return totalAlunos;
	}

	public void setTotalAlunos(Integer totalAlunos) {
		this.totalAlunos = totalAlunos;
	}

	public Integer getTotalProfessores() {
		return totalProfessores;
	}

	public void setTotalProfessores(Integer totalProfessores) {
		this.totalProfessores = totalProfessores;
	}
	
	
	
	

}
