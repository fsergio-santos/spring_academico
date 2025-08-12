package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academico.config.SpringProjectConfig;
import com.academico.models.service.LoginService;
import com.academico.models.service.components.GenerateCookie;
import com.academico.models.service.dto.request.LoginRequest;
import com.academico.models.service.dto.request.LoginVerifyOtp;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/rest")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private GenerateCookie generateCookie;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
		
		var loginResponse = loginService.login(loginRequest);
		 
        String accessTokenCookie = generateCookie.createCookie(SpringProjectConfig.ACCESS_TOKEN, loginResponse.getAccess_token(), 60*60*12*1000L);
        response.addHeader("Set-Cookie", accessTokenCookie);
        
        String refreshTokenCookie = generateCookie.createCookie(SpringProjectConfig.REFRESH_TOKEN,loginResponse.getRefresh_token(), 60*60*15*1000L);
        response.addHeader("Set-Cookie", refreshTokenCookie);
       
		return MensagemSistema.showMensagem(HttpStatus.OK, loginResponse, request);
	}

	@PostMapping(value = "/checkopt", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkEmailOtp(@RequestBody LoginVerifyOtp loginVerifyOtp, HttpServletRequest request) {
		loginService.checkEmailOtp(loginVerifyOtp);
		return MensagemSistema.showMensagem("Acesso verificado ", HttpStatus.OK, request);
	}

	@GetMapping(value = "/code/{email}")
	public ResponseEntity<?> opt(@PathVariable String email, HttpServletRequest request) {
		loginService.rquestOtp(email);
		return MensagemSistema.showMensagem("Verfique o seu e-mail ", HttpStatus.OK, request);
	}

	@GetMapping(value = "/logout/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(String email, HttpServletResponse response) {
		loginService.logout(email);
    	generateCookie.deleteCookie();
	}

}
