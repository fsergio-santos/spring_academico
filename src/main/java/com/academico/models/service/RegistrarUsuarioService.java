package com.academico.models.service;

import com.academico.models.service.dto.request.RegistrarRequest;

public interface RegistrarUsuarioService {
		
	public void save(RegistrarRequest registrarRequest);
	
	public void activate(String token);

}
