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

import com.academico.models.service.AvaliacaoService;
import com.academico.models.service.dto.request.AvaliacaoRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	
	@GetMapping(value="/listar")
	public  ResponseEntity<?> listaavaliacaos(HttpServletRequest request){
		var lista = avaliacaoService.listar();
		return MensagemSistema.showMensagem("Lista de avaliações gerada com sucesso ", HttpStatus.OK, lista, request);
		
	}

	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody AvaliacaoRequest avaliacaoRequest, HttpServletRequest request) {
	     var response = avaliacaoService.save(avaliacaoRequest);
	     return MensagemSistema.showMensagem("avaliacao gravado com sucesso ", HttpStatus.CREATED, response, request);
	    
	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AvaliacaoRequest avaliacaoRequest, HttpServletRequest request) {
		 var response = avaliacaoService.update(id, avaliacaoRequest);
		 return MensagemSistema.showMensagem("avaliacao alterado com sucesso ", HttpStatus.OK, response, request);
	 
	}

	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest request) {
	     avaliacaoService.deleteById(id);
	     return MensagemSistema.showMensagem("Avaliacao Excluído com sucesso!", HttpStatus.OK, request);
	}
	
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = avaliacaoService.findById(id);
	     return MensagemSistema.showMensagem("Avaliacao localizado com sucesso ", HttpStatus.OK, response, request);
	}


}
