package com.academico.models.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdatePasswordRequest {
	
	private String senha;
	private String confirmSenha;
	private String token;
	
	public UpdatePasswordRequest() {
		super();
	}

	public UpdatePasswordRequest(String senha, String confirmSenha, String token) {
		super();
		this.senha = senha;
		this.confirmSenha = confirmSenha;
		this.token = token;
	}
	
	@NotBlank(message = "A senha é obrigatória.")
	@Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
	@Pattern(
	    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
	    message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
	)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@NotBlank(message = "A senha é obrigatória.")
	@Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
	@Pattern(
	    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
	    message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
	)
	public String getConfirmSenha() {
		return confirmSenha;
	}
	
	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha;
	}
	
	@NotBlank(message = "Token inválido ")
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	

}
