package com.academico.models.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	private String email;
	private String senha;
	
	public LoginRequest() {
		
	}
	
	public LoginRequest(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	
	@NotBlank(message = "{EMAIL_REQUERIDO}")
	@Size(min = 5, max = 100, message = "{EMAIL_LENGTH}")
	@Email(message = "{EMAIL_INVALIDO}")	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", senha=" + senha + "]";
	}
	
	
	

}
