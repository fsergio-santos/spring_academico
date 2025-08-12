package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.AlunoService;
import com.academico.models.service.dto.request.AlunoRequest;
import com.academico.web.response.MensagemSistema;
import com.academico.web.swagger.AlunoControllerApi;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/aluno")
public class AlunoController implements AlunoControllerApi {
	
	@Autowired
	private AlunoService alunoService;
	
	@Override
	@GetMapping(value="/listar")
	public  ResponseEntity<?> listar(HttpServletRequest request){
		var lista = alunoService.listar();
		return MensagemSistema.showMensagem("Lista de alunos gerada com sucesso ", HttpStatus.OK, lista, request);
		
	}

	@Override	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody AlunoRequest alunoRequest, HttpServletRequest request) {
	     var response = alunoService.save(alunoRequest);
	     return MensagemSistema.showMensagem("Aluno gravado com sucesso ", HttpStatus.CREATED, response, request);
	    
	}

	@Override
	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AlunoRequest alunoRequest, HttpServletRequest request) {
		 var response = alunoService.update(id, alunoRequest);
		 return MensagemSistema.showMensagem("Aluno alterado com sucesso ", HttpStatus.OK, response, request);
	 
	}

	@Override
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest request) {
	     alunoService.deleteById(id);
	     return MensagemSistema.showMensagem("Aluno Excluído com sucesso!", HttpStatus.OK, request);
	}
	
	
	@Override
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = alunoService.findById(id);
	     return MensagemSistema.showMensagem("Aluno localizado com sucesso ", HttpStatus.OK, response, request);
	}
	
	



}
