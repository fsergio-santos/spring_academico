package com.academico.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConectadoController {

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value="/rest/conectado")
	public ResponseEntity<String> conectadoControler(){
		return ResponseEntity.ok("OK");
	}
}
