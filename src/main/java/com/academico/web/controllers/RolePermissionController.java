package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.RolePermissionService;
import com.academico.models.service.dto.request.RolePermissionRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/rest/rolepermission")
public class RolePermissionController {

	@Autowired
	private RolePermissionService rolePermissionService;
	
	
	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RolePermissionRequest rolePermissionRequest, HttpServletRequest request) {
		 var response = rolePermissionService.update(id, rolePermissionRequest);
		 return MensagemSistema.showMensagem("Permissões alteradas com sucesso ", HttpStatus.OK, response, request);
	 
	}
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = rolePermissionService.findById(id);
	     return MensagemSistema.showMensagem("Permissões localizada com sucesso ", HttpStatus.OK, response, request);
	}
	
}
