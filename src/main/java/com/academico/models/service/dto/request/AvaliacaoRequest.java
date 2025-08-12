package com.academico.models.service.dto.request;


public class AvaliacaoRequest {

	private Long idAvaliacao;
	private String codAvaliacao;
	private String descricaoAvaliacao;
	private Long idDisciplina;

	public AvaliacaoRequest() {
		super();
	}

	public AvaliacaoRequest(Long idAvaliacao, String codAvaliacao, String descricaoAvaliacao, Long idDisciplina) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.codAvaliacao = codAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.idDisciplina = idDisciplina;
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

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

}
