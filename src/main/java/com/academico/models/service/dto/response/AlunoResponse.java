package com.academico.models.service.dto.response;

public class AlunoResponse {

	private Long idAluno;
	private String codAluno;
	private String nomeAluno;
	private Integer idade;
	private Long idUsuario;
	private String nomeUsuario;

	public AlunoResponse() {
		super();
	}

	public AlunoResponse(Long idAluno, String codAluno, String nomeAluno, Integer idade, Long idUsuario,
			String nomeUsuario) {
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.idade = idade;
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
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
