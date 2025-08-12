package com.academico.models.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CidadeRequest {
	
	private Long idCidade;
	
	private String codCidade;
	private String nomeCidade;
	
	
	public CidadeRequest() {
	}
	
	
	public CidadeRequest(Long idCidade, String codCidade, String nomeCidade) {
		this.idCidade = idCidade;
		this.codCidade = codCidade;
		this.nomeCidade = nomeCidade;
	}
	public Long getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}
	
	@NotBlank(message = "O código da cidade é obrigatório.")
	//@Size(min = 5, max = 20, message = "O código da cidade deve ter entre 5 e 20 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "O código da cidade deve conter apenas letras e números.")
	public String getCodCidade() {
		return codCidade;
	}
	public void setCodCidade(String codCidade) {
		this.codCidade = codCidade;
	}
	
	@NotBlank(message = "O nome da cidade é obrigatório.")
	//@Size(min = 5, max = 100, message = "O nome da cidade deve ter entre 5 e 100 caracteres.")
	@Pattern(regexp = "^[a-zA-Zà-öø-ÿÀ-ÖØ-ßçÇãáéíóúãõàèìòù!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?\\s]*$", message = "O nome da cidade deve conter apenas letras ")
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	

}
