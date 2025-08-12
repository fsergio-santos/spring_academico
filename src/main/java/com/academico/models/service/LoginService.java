package com.academico.models.service;

import com.academico.models.service.dto.request.LoginRequest;
import com.academico.models.service.dto.request.LoginVerifyOtp;
import com.academico.models.service.dto.response.LoginResponse;

public interface LoginService {
	
	
	public LoginResponse login(LoginRequest loginRequest);
	
	public void checkEmailOtp( LoginVerifyOtp loginVerifyOtp );
	
	public void rquestOtp(String email);
	
	public void logout(String email);  
	
}
