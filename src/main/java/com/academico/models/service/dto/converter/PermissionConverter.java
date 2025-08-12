package com.academico.models.service.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.academico.models.enums.PermissionsName;
import com.academico.models.model.Permission;
import com.academico.models.service.dto.request.PermissionRequest;
import com.academico.models.service.dto.response.PermissionResponse;

public class PermissionConverter {

	
	public static Permission toPermission(PermissionRequest permissionRequest) {
		return new Permission(
				permissionRequest.getIdPermission(),
				PermissionsName.valueOf(permissionRequest.getNomePermission())
		);
	}
	
	public static PermissionResponse toPermissionResponse(Permission permission) {
		return new PermissionResponse(
				permission.getIdPermission(),
				permission.getNomePermission().getNomePermissions()
		);
				
	}

	public static List<PermissionResponse> toListaPermissionResponse(List<Permission> listaPermission) {
		 List<PermissionResponse> listaPermissionResponse = new ArrayList<PermissionResponse>();
	     for (Permission permission :listaPermission ) {
		      var permissionResponse = toPermissionResponse(permission);
			  listaPermissionResponse.add(permissionResponse);
		 }
		 return listaPermissionResponse;
	}
}
