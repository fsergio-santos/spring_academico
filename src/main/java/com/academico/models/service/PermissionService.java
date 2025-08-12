package com.academico.models.service;

import com.academico.models.service.dto.request.PermissionRequest;
import com.academico.models.service.dto.response.PermissionResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface PermissionService extends GenericService<PermissionRequest, PermissionResponse, Long> {
	
	
	public Page<PermissionResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );

}
