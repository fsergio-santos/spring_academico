package com.academico.models.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginVerifyOtp {
	
	private String email;
	private String otp;
	
	public LoginVerifyOtp() {
		
	}
	
	public LoginVerifyOtp(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
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
	
	@NotBlank(message = "O código de verificação é obrigatório ")
	public String getOtp() {
		return otp;
	}
	
	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "LoginVerifyOtp [email=" + email + ", otp=" + otp + "]";
	}
	
	

}
