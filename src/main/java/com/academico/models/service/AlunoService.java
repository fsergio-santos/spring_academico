package com.academico.models.service;

import com.academico.models.service.dto.request.AlunoRequest;
import com.academico.models.service.dto.response.AlunoResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface AlunoService extends GenericService<AlunoRequest, AlunoResponse, Long>{
	
	public Page<AlunoResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );
	
}
