package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.RegistrarUsuarioService;
import com.academico.models.service.dto.request.RegistrarRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/registrar")
public class RegistrarUsuarioController {

	@Autowired
	private RegistrarUsuarioService registrarUsuarioService;
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid RegistrarRequest registrarRequest, HttpServletRequest request) {
	     registrarUsuarioService.save(registrarRequest);
	     return MensagemSistema.showMensagem("Usuario registrado com sucesso ", HttpStatus.CREATED, request);
	}
	
	@GetMapping(value="/ativar")
	public void ativarUsuario(@RequestParam String token) {
		registrarUsuarioService.activate(token);
	}
	
	
	
}
