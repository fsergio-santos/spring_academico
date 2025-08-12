package com.academico.models.service.dto.request;

public class RefreshTokenRequest {

	private String refresh_token;
	
	public RefreshTokenRequest() {
	}

	public RefreshTokenRequest(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}
	
	
}
