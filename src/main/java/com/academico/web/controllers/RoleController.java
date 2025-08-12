package com.academico.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.RoleService;
import com.academico.models.service.dto.request.RoleRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/rest/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(HttpServletRequest request) {
	     var lista = roleService.listar();
	 	 return MensagemSistema.showMensagem("Lista de roles gerada com sucesso", HttpStatus.OK,lista, request);
	}
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid RoleRequest roleRequest, HttpServletRequest request) {
	     var response = roleService.save(roleRequest);
	     return MensagemSistema.showMensagem("Role registrado com sucesso ", HttpStatus.CREATED, response, request);

	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, 
			                        @RequestBody 
			                        @Valid RoleRequest roleRequest, HttpServletRequest request) {
	     var response = roleService.update(id, roleRequest);
	     return MensagemSistema.showMensagem("Role alterado com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@PatchMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateField(@PathVariable Long id, 
			                        @RequestBody 
			                        Map<String, Object> dataRequest, HttpServletRequest request) {
		 Map<String, Object> dataRequestConvertidos = new HashMap<String, Object>();
	     dataRequest.forEach(dataRequestConvertidos::put);
	     var response = roleService.updateField(id, dataRequestConvertidos);
	     
	     return MensagemSistema.showMensagem("Role alterado com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest request) {
	     roleService.deleteById(id);
	     return MensagemSistema.showMensagem("Role excluído com sucesso ", HttpStatus.OK, request);
	}
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = roleService.findById(id);
	     return MensagemSistema.showMensagem("Role localizado com sucesso ", HttpStatus.OK, response, request);
	}
	
		

}
