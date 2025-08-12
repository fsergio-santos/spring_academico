package com.academico.web.controllers;

import java.util.List;

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

import com.academico.models.service.UsuarioRolePermissionService;
import com.academico.models.service.dto.request.UsuarioRolePermissionRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/direito")
public class UsuarioRoleResponseController {
	
	@Autowired
	private UsuarioRolePermissionService usuarioRolePermissionService;
	
	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(HttpServletRequest request) {
	     var lista = usuarioRolePermissionService.listar();
	 	 return MensagemSistema.showMensagem("Lista de direitos de acesso gerada com sucesso", HttpStatus.OK,lista, request);
	}
	
	
	@PutMapping(value="/salvar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id,
			                        @RequestBody @Valid List<UsuarioRolePermissionRequest> usuarioRequest, HttpServletRequest request) {
		
	     usuarioRolePermissionService.update( id, usuarioRequest );
	     
	     return MensagemSistema.showMensagem("Direitos de acesso do usuário registrado com sucesso ", HttpStatus.CREATED, request);
		
		 
	}
	
	
    @GetMapping(value="/roles-permissions/{id}") 
	public ResponseEntity<?> loadRolesAndPermissionById(@PathVariable Long id, HttpServletRequest request){
     	var response = usuarioRolePermissionService.loadRolesAndPermissionById(id);
    	return MensagemSistema.showMensagem("Usuario localizado com sucesso ", HttpStatus.OK, response, request);
	}
    

}
