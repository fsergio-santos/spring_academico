package com.academico.models.repository.impl;

import java.util.List;

import com.academico.models.service.pagination.Pageable;

public interface DisciplinaRepositoryCustom {
	
	public List<Object[]> buscarRegistrosPaginados(String key, Pageable pageable);
	
	public Long totalRegistrosDisciplina(String key );
	
	
}

