package com.academico.models.service;

import java.util.List;

import com.academico.models.service.dto.request.UsuarioRolePermissionRequest;
import com.academico.models.service.dto.response.UsuarioRolePermissionResponse;
import com.academico.models.service.pagination.Page;

public interface UsuarioRolePermissionService  {
	
	public List<UsuarioRolePermissionResponse> listar();
	
	public void save(UsuarioRolePermissionRequest entity);
	
	public void update(Long id, List<UsuarioRolePermissionRequest> entity);

	public Page<UsuarioRolePermissionResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props );
	
	public List<UsuarioRolePermissionResponse> loadRolesAndPermissionById(Long idUsuario);
}
