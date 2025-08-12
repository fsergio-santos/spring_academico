package com.academico.models.service.dto.response;

public class AvaliacaoResponse {

	private Long idAvaliacao;
	private String codAvaliacao;
	private String descricaoAvaliacao;
	private Long idDeisciplina;
	private String nomeDisciplina;
	private Long idProfessor;
	private String nomeProfessor;

	public AvaliacaoResponse() {

	}

	public AvaliacaoResponse(Long idAvaliacao, String codAvaliacao, String descricaoAvaliacao, Long idDeisciplina,
			String nomeDisciplina, Long idProfessor, String nomeProfessor) {
		this.idAvaliacao = idAvaliacao;
		this.codAvaliacao = codAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.idDeisciplina = idDeisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.idProfessor = idProfessor;
		this.nomeProfessor = nomeProfessor;
	}

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public String getCodAvaliacao() {
		return codAvaliacao;
	}

	public void setCodAvaliacao(String codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}

	public String getDescricaoAvaliacao() {
		return descricaoAvaliacao;
	}

	public void setDescricaoAvaliacao(String descricaoAvaliacao) {
		this.descricaoAvaliacao = descricaoAvaliacao;
	}

	public Long getIdDeisciplina() {
		return idDeisciplina;
	}

	public void setIdDeisciplina(Long idDeisciplina) {
		this.idDeisciplina = idDeisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

}
