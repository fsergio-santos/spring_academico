package com.academico.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.academico.models.service.UsuarioService;
import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.web.response.MensagemSistema;
import com.academico.web.swagger.UsuarioControllerApi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/rest/usuario")
public class UsuarioController implements UsuarioControllerApi {
	
	@Autowired
	private UsuarioService usuarioService;
	
    @Override
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(HttpServletRequest request) {
	     var lista = usuarioService.listar();
	 	 return MensagemSistema.showMensagem(
	 			 "Lista de usuários gerada com sucesso", 
	 			 HttpStatus.OK,
	 			 lista, 
	 			 request
	 	 );
	}
	

	@Override
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
						   		 @RequestParam(value="props", defaultValue = "idUsuario", 
						         required = false) String props,
						   		 HttpServletRequest request) {
		
		var lista = usuarioService.listaPaginada(key, page, pageSize, dir, props);
		
		return MensagemSistema.showMensagem("Lista de usuário gerada com sucesso", HttpStatus.OK, lista, request);
		
		
	}
	
	
	@Override
	@GetMapping(value="/pagination")
	public ResponseEntity<?> getUsuarioPagination(
			 @RequestParam(value = "key", 
			 required = false ) String key,
			 @RequestParam(value="page", 
	         defaultValue = SpringProjectConfig.PAGE_NUMBER, 
	         required = false) Integer page,
	   	 	 @RequestParam(value="pageSize", defaultValue = SpringProjectConfig.PAGE_SIZE, 
	         required = false) Integer pageSize,
	   		 @RequestParam(value="dir", defaultValue = SpringProjectConfig.DIR, 
			 required = false) String dir,
	   		 @RequestParam(value="props", defaultValue = "idUsuario", 
	         required = false) String props,
	   		 HttpServletRequest request) {

			var lista = usuarioService.getUsuarioPagination(key, page, pageSize, dir, props);

			return MensagemSistema.showMensagem("Lista de usuário gerada com sucesso", HttpStatus.OK, lista, request);

	} 
	
	@Override
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid UsuarioRequest usuarioRequest, HttpServletRequest request) {
	     var response = usuarioService.save(usuarioRequest);
	     return MensagemSistema.showMensagem("Usuario registrado com sucesso ", HttpStatus.CREATED, response, request);

	}

	@Override
	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, 
			                        @RequestBody 
			                        @Valid UsuarioRequest usuarioRequest, HttpServletRequest request) {
	     var response = usuarioService.update(id, usuarioRequest);
	     return MensagemSistema.showMensagem("Usuario alterado com sucesso ", HttpStatus.OK, response, request);
	}
	
	@Override
	@PreAuthorize("hasAuthority('UPDATE') and #id == authentication.principal.id")
	@PatchMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateField(@PathVariable Long id, 
			                        @RequestBody 
			                        Map<String, Object> dataRequest, HttpServletRequest request) {
		 Map<String, Object> dataRequestConvertidos = new HashMap<String, Object>();
	     dataRequest.forEach(dataRequestConvertidos::put);
	     var response = usuarioService.updateField(id, dataRequestConvertidos);
	     
	     return MensagemSistema.showMensagem("Usuario alterado com sucesso ", HttpStatus.OK, response, request);
	}
	
	
	@Override
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest request) {
	     usuarioService.deleteById(id);
	     return MensagemSistema.showMensagem("Usuario Excluído com sucesso ", HttpStatus.OK, request);
	}
	
	
	
	@Override
	@PreAuthorize("hasAuthority('READ')")
	@PostAuthorize("#id == authentication.principal.id")
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
	     var response = usuarioService.findById(id);
	     return MensagemSistema.showMensagem("Usuario localizado com sucesso ", HttpStatus.OK, response, request);
	}
	
	@Override
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/totais")
	public ResponseEntity<?> showTotalUsuarios(HttpServletRequest request){
		Map<String, Long> totaisUsuario = usuarioService.calculateTotalUsuario();
	    return MensagemSistema.showMensagem(HttpStatus.OK, totaisUsuario, request);
	}

	
	@Override
	@GetMapping(value="/buscarusuario/{id}")
	public ResponseEntity<?> findUsuarioById(@PathVariable Long id, HttpServletRequest request) {
	     var response = usuarioService.findById(id);
	     return MensagemSistema.showMensagem("Usuario localizado com sucesso ", HttpStatus.OK, response, request);
	}
	

}
