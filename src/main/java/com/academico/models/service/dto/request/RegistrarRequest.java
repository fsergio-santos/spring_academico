package com.academico.models.service.dto.request;

public class RegistrarRequest {
	
	private String nomeUsuario;
	private String email;
	private String senha;
	private String confirmSenha;
	
	public RegistrarRequest() {
	}

	public RegistrarRequest(String nomeUsuario, String email, String senha, String confirmSenha) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.confirmSenha = confirmSenha;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmSenha() {
		return confirmSenha;
	}

	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha;
	}
	
   	
	

}
