package com.academico.models.repository.impl;

import java.util.List;

import com.academico.models.model.Cidade;
import com.academico.models.service.pagination.Pageable;

public interface CidadeRepositoryCustom {
	
	public List<Cidade> buscarRegistrosPaginados(String key, Pageable pageable);	
	
	public List<Cidade> listaCidades();
	

}
