package com.academico.models.service.dto.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class FotoRequest implements Serializable {


	private static final long serialVersionUID = 6782560364488506979L;

	private String id;
	private String nomeArquivo;
	private String contentType;
	private MultipartFile foto; 
	
	
	public FotoRequest() {
	}

	public FotoRequest(String id, String nomeArquivo, String contentType, MultipartFile foto) {
		this.id = id;
		this.nomeArquivo = nomeArquivo;
		this.contentType = contentType;
		this.foto = foto;
	}
	
	public FotoRequest(String id, String nomeArquivo) {
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
	public MultipartFile getFoto() {
		return foto;
	}
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
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
		return "FotoRequest [id=" + id + ", nomeArquivo=" + nomeArquivo + ", contentType=" + contentType + ", foto="
				+ foto + "]";
	}
	
	
	
}
