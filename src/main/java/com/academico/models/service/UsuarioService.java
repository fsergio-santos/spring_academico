package com.academico.models.service;


import java.util.Map;

import com.academico.models.service.dto.request.AlterarSenhaRequest;
import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.models.service.dto.response.UsuarioResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface UsuarioService extends GenericService<UsuarioRequest, UsuarioResponse, Long>{
	
	public Map<String, Long> calculateTotalUsuario(); 
	
	public Page<UsuarioResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );
	
	public Page<UsuarioResponse> getUsuarioPagination(String key, Integer page, Integer pageSize, String dir, String props);
	
	public void alterarSenha(AlterarSenhaRequest alterarSenhaRequest);
	
	public UsuarioResponse findUsuarioById(Long id);
	
	
	
}
