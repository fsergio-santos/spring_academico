package com.academico.models.service.dto.converter;

import com.academico.models.service.dto.response.RefreshTokenResponse;

public class RefreshTokenConverter {

	
	public static RefreshTokenResponse toRefreshTokenResponse(String access_token, String refresh_token) {
		return new RefreshTokenResponse(
				access_token,
				refresh_token
				);
	}
	
}
