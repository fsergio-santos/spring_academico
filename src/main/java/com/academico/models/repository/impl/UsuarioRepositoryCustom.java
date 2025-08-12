package com.academico.models.repository.impl;

import com.academico.models.service.dto.response.ListaPagination;
import com.academico.models.service.pagination.Pageable;

public interface UsuarioRepositoryCustom {
	
	public ListaPagination buscarRegistrosPaginados(String key, Pageable pageable);
	
	public Long totalRegistrosUsuarios(String key );
	
	public ListaPagination getUsuarioPagination(String key, Pageable pageable);
	
}

