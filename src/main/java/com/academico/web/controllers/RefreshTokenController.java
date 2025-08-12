package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.RefreshTokenService;
import com.academico.models.service.dto.request.RefreshTokenRequest;
import com.academico.web.response.MensagemSistema;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/rest")
public class RefreshTokenController {

	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping(value="/refreshtoken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest, HttpServletRequest request ){
		var response = refreshTokenService.refreshToken(refreshTokenRequest);
		return MensagemSistema.showMensagem(HttpStatus.OK, response, request);
	}
	
}
