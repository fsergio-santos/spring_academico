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
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.UpdatePasswordService;
import com.academico.models.service.dto.request.UpdatePasswordRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/update")
public class UpdatePasswordController {

	@Autowired
	private UpdatePasswordService updatePasswordService;
	
	@GetMapping(value="/senha/{email}")
	public ResponseEntity<?> save(@PathVariable String email, HttpServletRequest request) {
	     updatePasswordService.updatePassword(email);
	     return MensagemSistema.showMensagem("Verifique o seu e-mail ", HttpStatus.OK,request);
	}
	
	@PostMapping(value="/password", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {
		updatePasswordService.changePassword(updatePasswordRequest);
		return MensagemSistema.showMensagem("Verifique o seu e-mail ", HttpStatus.OK,request);
	}
	
	@GetMapping(value="/validatetoken/{token}")
	public ResponseEntity<?> validateToken(@PathVariable String token, HttpServletRequest request){
		updatePasswordService.validateToken(token);
		return MensagemSistema.showMensagem("Acesso permitido ", HttpStatus.OK,request);
	}
}
