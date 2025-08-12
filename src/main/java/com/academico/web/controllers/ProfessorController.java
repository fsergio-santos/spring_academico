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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academico.config.SpringProjectConfig;
import com.academico.models.service.ProfessorService;
import com.academico.models.service.dto.request.ProfessorRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	
	@GetMapping(value="/listar")
	public  ResponseEntity<?> listaAlunos(HttpServletRequest request){
		var lista = professorService.listar();
		return MensagemSistema.showMensagem("Lista de professores gerada com sucesso ", HttpStatus.OK, lista, request);
		
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
						   		 @RequestParam(value="props", defaultValue = "idProfessor", 
						         required = false) String props,
						   		 HttpServletRequest request) {
		
		var lista = professorService.listaPaginada(key, page, pageSize, dir, props);
		
		return MensagemSistema.showMensagem("Lista de professores gerada com sucesso", HttpStatus.OK, lista, request);
		
		
	}
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody ProfessorRequest professorRequest,HttpServletRequest request) {
	     var response = professorService.save(professorRequest);
	     return MensagemSistema.showMensagem("Professor registrado com sucesso ", HttpStatus.CREATED, response, request);
	    
	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProfessorRequest professorRequest,HttpServletRequest request) {
		 var response = professorService.update(id, professorRequest);
		 return MensagemSistema.showMensagem("Professor alterado com sucesso!", HttpStatus.OK, response, request);
	 
	}

	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id,HttpServletRequest request) {
	     professorService.deleteById(id);
	     return MensagemSistema.showMensagem("Professor Excluído com sucesso!", HttpStatus.OK, request);
	}
	
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id,HttpServletRequest request) {
	     var response = professorService.findById(id);
	     return MensagemSistema.showMensagem("Professor localizado com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	

}
