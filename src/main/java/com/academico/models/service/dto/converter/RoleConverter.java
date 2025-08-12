package com.academico.models.service.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.academico.models.enums.RoleName;
import com.academico.models.model.Role;
import com.academico.models.service.dto.request.RoleRequest;
import com.academico.models.service.dto.response.RoleResponse;

public class RoleConverter {

	
	public static Role toRole(RoleRequest roleRequest) {
		return new Role(
				roleRequest.getIdRole(),
				RoleName.valueOf(roleRequest.getNomeRole())
		);
	}
	
	public static RoleResponse toRoleResponse(Role role) {
		return new RoleResponse(
				role.getIdRole(),
				role.getNomeRole().getNome()
		);
				
	}
	
	public static List<RoleResponse> toListaRoleResponse(List<Role> listaRole){
        
		   List<RoleResponse> listaRoleResponse = new ArrayList<RoleResponse>();
		
		   for (Role role :listaRole ) {
			   var roleResponse = toRoleResponse(role);
			   listaRoleResponse.add(roleResponse);
		   }
		   return listaRoleResponse;
		   
		
	}
	
	
	
}
