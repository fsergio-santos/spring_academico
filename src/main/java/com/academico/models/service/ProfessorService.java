package com.academico.models.service;

import com.academico.models.service.dto.request.ProfessorRequest;
import com.academico.models.service.dto.response.ProfessorResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface ProfessorService extends GenericService<ProfessorRequest,ProfessorResponse,Long>{
	
	public Page<ProfessorResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );

}
