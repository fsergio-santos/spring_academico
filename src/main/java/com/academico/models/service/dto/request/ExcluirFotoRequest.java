package com.academico.models.service.dto.request;

import java.io.Serializable;


public class ExcluirFotoRequest implements Serializable {


	private static final long serialVersionUID = 6782560364488506979L;

	private String id;
	private String nomeArquivo;

	
	
	public ExcluirFotoRequest() {
	}

	
	public ExcluirFotoRequest(String id, String nomeArquivo) {
		super();
		this.id = id;
		this.nomeArquivo = nomeArquivo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	


	public String getNomeArquivo() {
		return nomeArquivo;
	}


	@Override
	public String toString() {
		return "ExcluirFotoRequest [id=" + id + ", nomeArquivo=" + nomeArquivo + "]";
	}


	
	
	
}
