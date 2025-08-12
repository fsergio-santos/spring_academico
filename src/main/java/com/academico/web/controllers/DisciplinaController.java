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

import com.academico.models.service.DisciplinaService;
import com.academico.models.service.dto.request.DisciplinaRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/rest/disciplina")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(HttpServletRequest request) {
	     var lista = disciplinaService.listar();
	 	 return MensagemSistema.showMensagem("Lista de discíplinas gerada com sucesso", HttpStatus.OK,lista,request);
	}
			
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid DisciplinaRequest disciplinaRequest,HttpServletRequest request) {
	     var response = disciplinaService.save(disciplinaRequest);
	     return MensagemSistema.showMensagem("Disciplina registrada com sucesso ", HttpStatus.CREATED, response, request);

	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, 
			                        @RequestBody 
			                        @Valid DisciplinaRequest disciplinaRequest,HttpServletRequest request) {
	     var response = disciplinaService.update(id, disciplinaRequest);
	     return MensagemSistema.showMensagem("Disciplina alterada com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@PatchMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateField(@PathVariable Long id, 
			                        @RequestBody 
			                        Map<String, Object> dataRequest,HttpServletRequest request) {
		 Map<String, Object> dataRequestConvertidos = new HashMap<String, Object>();
	     dataRequest.forEach(dataRequestConvertidos::put);
	     var response = disciplinaService.updateField(id, dataRequestConvertidos);
	     return MensagemSistema.showMensagem("Disciplina alterada com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id,HttpServletRequest request) {
	     disciplinaService.deleteById(id);
	     return MensagemSistema.showMensagem("Disciplina excluída com sucesso ", HttpStatus.OK, request);
	}
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id,HttpServletRequest request) {
	     var response = disciplinaService.findById(id);
	     return MensagemSistema.showMensagem("Disciplina localizada com sucesso ", HttpStatus.OK, response, request);
	}
	

	

}
