package com.academico.models.service;

import com.academico.models.service.dto.request.RolePermissionRequest;
import com.academico.models.service.dto.response.RolePermissionResponse;

public interface RolePermissionService  {
	
	public RolePermissionResponse update(Long id, RolePermissionRequest entity);
	
	public RolePermissionResponse findById(Long id);

}
