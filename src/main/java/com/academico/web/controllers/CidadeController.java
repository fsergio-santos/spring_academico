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

import com.academico.models.service.CidadeService;
import com.academico.models.service.dto.request.CidadeRequest;
import com.academico.web.response.MensagemSistema;
import com.academico.web.swagger.CidadeControllerApi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/cidade")
public class CidadeController implements CidadeControllerApi {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Override
	@GetMapping(value="/listar")
	public  ResponseEntity<?> listar(HttpServletRequest request){
		var lista = cidadeService.listar();
		return MensagemSistema.showMensagem(null, HttpStatus.OK, lista, request);
	}

	
	@Override
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody  @Valid CidadeRequest cidadeRequest,HttpServletRequest request) {
	     var response = cidadeService.save(cidadeRequest);
	     return MensagemSistema.showMensagem("Cidade registrada com sucesso ", HttpStatus.CREATED,response, request);
	}

	@Override
	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CidadeRequest cidadeRequest,HttpServletRequest request) {
	    var response = cidadeService.update(id, cidadeRequest);
	    return MensagemSistema.showMensagem("Cidade alterada com sucesso ", HttpStatus.OK, response, request);
	}

	
    @Override
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id,HttpServletRequest request) {
	     cidadeService.deleteById(id);
	     return MensagemSistema.showMensagem("Cidade Excluída com sucesso ", HttpStatus.OK, request);
	}
	
    @Override
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = cidadeService.findById(id);
	     return MensagemSistema.showMensagem("Cidade Localizada ", HttpStatus.OK, response, request);
	    
	}
	
	
}
