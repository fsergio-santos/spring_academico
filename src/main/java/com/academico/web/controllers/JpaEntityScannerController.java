package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.JpaEntityScannerService;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/rolepermission")
public class JpaEntityScannerController {
	
	@Autowired
	private JpaEntityScannerService jpaEntityScannerService;
	
	@GetMapping(value="/listar")
	public ResponseEntity<?> listarEntidadesRolePermissao(HttpServletRequest request){
		
		var lista = jpaEntityScannerService.listarEntidadesRolePermissao();
		
		return MensagemSistema.showMensagem(
	 			 "Lista dos recursos gerada com sucesso", 
	 			 HttpStatus.OK,
	 			 lista, 
	 			 request
	 	 );
		
	}

}
