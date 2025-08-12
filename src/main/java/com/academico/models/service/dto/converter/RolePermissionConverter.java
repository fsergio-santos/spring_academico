package com.academico.models.service.dto.converter;

import com.academico.models.model.RolePermission;
import com.academico.models.service.dto.request.RolePermissionRequest;
import com.academico.models.service.dto.response.RolePermissionResponse;

public class RolePermissionConverter {

	public static RolePermissionResponse toRolePermissionResponse(RolePermission rolePermission) {
		return new RolePermissionResponse(
				rolePermission.getIdRolePermission(),
				rolePermission.getRole(),
				rolePermission.getTarget(),
				rolePermission.getRead(),
				rolePermission.getCreate(),
				rolePermission.getUpdate(),
				rolePermission.getDelete()
	 	);
	}
	
	
	public static RolePermission toRolePermission(RolePermissionRequest rolePermissionRequest) {
		return new RolePermission(
				rolePermissionRequest.getIdRolePermission(),
				rolePermissionRequest.getRole(),
				rolePermissionRequest.getTarget(),
				rolePermissionRequest.getRead(),
				rolePermissionRequest.getCreate(),
				rolePermissionRequest.getUpdate(),
				rolePermissionRequest.getDelete()
	 	);
	}

}
