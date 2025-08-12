package com.academico.models.service;

import java.util.List;

import com.academico.models.service.dto.response.RolePermissionResponse;

public interface JpaEntityScannerService {
	
	
	public List<RolePermissionResponse> listarEntidadesRolePermissao();

}
