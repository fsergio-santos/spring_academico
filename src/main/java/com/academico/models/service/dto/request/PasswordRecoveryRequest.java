package com.academico.models.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordRecoveryRequest {

	private String email;
	
	public PasswordRecoveryRequest() {
	}

	public PasswordRecoveryRequest(String email) {
		this.email = email;
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


	@Override
	public String toString() {
		return "PasswordRecoveryRequest [email=" + email + "]";
	}
	
	
	
	
}
