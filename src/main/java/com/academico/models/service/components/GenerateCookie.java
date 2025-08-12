/**
 * 
 */
package com.academico.models.service.components;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.academico.config.SpringProjectConfig;

@Component
public class GenerateCookie {

	public String createCookie(String typeToken, String token, Long duration) {
		return ResponseCookie.from(typeToken, token)
				             .maxAge(duration)
				             .httpOnly(true)
				             .secure(false)
				             .sameSite("none")
				             .path("/")
				             .build()
				             .toString();
	}

	
	public void deleteCookie() {
		ResponseCookie.from(SpringProjectConfig.ACCESS_TOKEN, "")
		              .maxAge(0)
		              .httpOnly(true)
		              .secure(false)
		              .sameSite("none")
		              .path("/")
		              .build();
		
		ResponseCookie.from(SpringProjectConfig.REFRESH_TOKEN, "")
		              .maxAge(0)
		              .httpOnly(true)
		              .secure(false)
		              .sameSite("none")
		              .path("/")
		              .build();
	}

}
