package com.academico.models.service;

import com.academico.models.service.dto.request.RefreshTokenRequest;
import com.academico.models.service.dto.response.RefreshTokenResponse;

public interface RefreshTokenService {

	
	public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
	
}
