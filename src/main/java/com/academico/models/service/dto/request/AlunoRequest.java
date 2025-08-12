package com.academico.models.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AlunoRequest {

	
	private Long    idAluno;
	private String  codAluno;
	private String  nomeAluno; 
	private Integer idade;
	private Long    idUsuario;
	
	
	
	public AlunoRequest() {
		super();
	}

	
	
	public AlunoRequest(Long idAluno, String codAluno, String nomeAluno, Integer idade, Long idUsuario) {
		super();
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.idade = idade;
		this.idUsuario = idUsuario;
	}



	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	
	@NotBlank(message = "O código do aluno é obrigatório.")
	@Size(min = 5, max = 20, message = "O código do usuário deve ter entre 5 e 20 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "O código do aluno deve conter apenas letras e números.")
	public String getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}
	
	@NotBlank(message = "O nome do aluno é obrigatório.")
	@Size(min = 5, max = 100, message = "O nome do usuário deve ter entre 5 e 100 caracteres.")
	@Pattern(regexp = "^[A-Za]+$", message = "O nome do usuário deve conter apenas letras ")
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	
	@NotBlank(message = "A idade do aluno é obrigatório.")
	@Pattern(regexp = "^[0-9]+$", message = "O nome do usuário deve conter apenas letras ")
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}


	@NotBlank(message = "A código do usuário é obrigatório.")
	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}


