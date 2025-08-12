package com.academico.models.service;

import com.academico.models.service.dto.request.CidadeRequest;
import com.academico.models.service.dto.response.CidadeResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface CidadeService extends GenericService<CidadeRequest,CidadeResponse,Long>{
	
	public Page<CidadeResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );

		
}
