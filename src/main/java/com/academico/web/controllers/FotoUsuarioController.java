package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.service.UsuarioFotoService;
import com.academico.models.service.dto.request.ExcluirFotoRequest;
import com.academico.models.service.dto.request.FotoRequest;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping(value="/rest/foto")
public class FotoUsuarioController  {
	
	@Autowired
	private UsuarioFotoService usuarioFotoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value="/salvar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
								 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> uploadFoto(FotoRequest fotoRequest, HttpServletRequest request) {
		
		var fotoResponse  = usuarioFotoService.storeUsuarioFoto(fotoRequest);
		
		return MensagemSistema.showMensagem("Imagem cadastrada", HttpStatus.CREATED, fotoResponse, request);
	
	}
	
	
	@DeleteMapping(value="/excluir", consumes = MediaType.APPLICATION_JSON_VALUE,
			  					     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> excluirFoto(@RequestBody  ExcluirFotoRequest fotoRequest, HttpServletRequest request) {
		
		var fotoResponse = usuarioFotoService.deleteUsuarioFoto(fotoRequest);
		return MensagemSistema.showMensagem("Imagem excluída!", HttpStatus.OK, fotoResponse, request);

	}
	
	
	@GetMapping("/f/{nomeFoto:.+}")
	public byte[] recuperarFoto(@PathVariable String nomeFoto) {
		return usuarioFotoService.recuperarFoto(nomeFoto);
	}


	
}
