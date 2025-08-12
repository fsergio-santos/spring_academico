package com.academico.models.service.dto.response;

import java.io.Serializable;


public class FotoResponse implements Serializable{
	
	private static final long serialVersionUID = 8590689959301715860L;
	
	private String nomeArquivo;    
    private String contentType;   
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Override
	public String toString() {
		return "Foto [nomeArquivo=" + nomeArquivo + ", contentType=" + contentType
				+ ", id=" + id + "]";
	}
	

}
