package com.academico.models.service;

import com.academico.models.service.dto.request.DisciplinaRequest;
import com.academico.models.service.dto.response.DisciplinaResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface DisciplinaService extends GenericService<DisciplinaRequest, DisciplinaResponse, Long> {
	
	public Page<DisciplinaResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );

}
