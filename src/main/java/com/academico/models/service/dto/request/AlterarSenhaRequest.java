package com.academico.models.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AlterarSenhaRequest {	
	
	private Long   idUsuario;
	private String senha;
	private String confirmSenha;
	
	public AlterarSenhaRequest() {
		super();
	}

	
	public AlterarSenhaRequest(Long idUsuario, String senha, String confirmSenha) {
		super();
		this.idUsuario = idUsuario;
		this.senha = senha;
		this.confirmSenha = confirmSenha;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

}
