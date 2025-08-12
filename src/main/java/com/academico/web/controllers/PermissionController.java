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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academico.config.SpringProjectConfig;
import com.academico.models.service.PermissionService;
import com.academico.models.service.dto.request.PermissionRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/rest/permissao")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(HttpServletRequest request) {
	     var lista = permissionService.listar();
	 	 return MensagemSistema.showMensagem("Lista de permissão gerada com sucesso", HttpStatus.OK,lista, request);
	}
	

	@GetMapping(value="/listagem")
	public ResponseEntity<?> listaPaginada(
								 @RequestParam(value = "key", 
								 required = false ) String key,
								 @RequestParam(value="page", 
						         defaultValue = SpringProjectConfig.PAGE_NUMBER, 
						         required = false) Integer page,
						   	 	 @RequestParam(value="pageSize", defaultValue = SpringProjectConfig.PAGE_SIZE, 
						         required = false) Integer pageSize,
						   		 @RequestParam(value="dir", defaultValue = SpringProjectConfig.DIR, 
								 required = false) String dir,
						   		 @RequestParam(value="props", defaultValue = "idPermission", 
						         required = false) String props,
						   		 HttpServletRequest request) {
		
		var lista = permissionService.listaPaginada(key, page, pageSize, dir, props);
		
		return MensagemSistema.showMensagem("Lista de permissão gerada com sucesso", HttpStatus.OK, lista, request);
		
		
	}
	
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid PermissionRequest permissionRequest, HttpServletRequest request) {
	     var response = permissionService.save(permissionRequest);
	     return MensagemSistema.showMensagem("Permissão registrado com sucesso ", HttpStatus.CREATED, response, request);

	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, 
			                        @RequestBody 
			                        @Valid PermissionRequest permissionRequest, HttpServletRequest request) {
	     var response = permissionService.update(id, permissionRequest);
	     return MensagemSistema.showMensagem("Permissão alterada com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@PatchMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateField(@PathVariable Long id, 
			                        @RequestBody 
			                        Map<String, Object> dataRequest, HttpServletRequest request) {
		 Map<String, Object> dataRequestConvertidos = new HashMap<String, Object>();
	     dataRequest.forEach(dataRequestConvertidos::put);
	     var response = permissionService.updateField(id, dataRequestConvertidos);
	     
	     return MensagemSistema.showMensagem("Permissão alterada com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest request) {
	     permissionService.deleteById(id);
	     return MensagemSistema.showMensagem("Permissão excluída com sucesso ", HttpStatus.OK, request);
	}
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = permissionService.findById(id);
	     return MensagemSistema.showMensagem("Permissão localizada com sucesso ", HttpStatus.OK, response,request);
	}
	
		

}
