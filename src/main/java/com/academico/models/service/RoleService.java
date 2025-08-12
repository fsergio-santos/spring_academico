package com.academico.models.service;

import com.academico.models.service.dto.request.RoleRequest;
import com.academico.models.service.dto.response.RoleResponse;
import com.academico.models.service.generic.GenericService;
import com.academico.models.service.pagination.Page;

public interface RoleService extends GenericService<RoleRequest, RoleResponse, Long> {
	
	public Page<RoleResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );

}
