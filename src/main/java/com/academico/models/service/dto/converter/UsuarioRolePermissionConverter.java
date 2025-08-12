package com.academico.models.service.dto.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Permission;
import com.academico.models.model.Role;
import com.academico.models.model.Usuario;
import com.academico.models.model.UsuarioRolePermission;
import com.academico.models.model.UsuarioRolePermissionId;
import com.academico.models.service.dto.request.UsuarioRolePermissionRequest;
import com.academico.models.service.dto.response.UsuarioRolePermissionResponse;

public class UsuarioRolePermissionConverter {

	
	public static UsuarioRolePermissionId toUsuarioRolePermission(UsuarioRolePermissionRequest usuarioRolePermissionRequest) {
		return new UsuarioRolePermissionId(
				usuarioRolePermissionRequest.getIdUsuario(),
				usuarioRolePermissionRequest.getIdRole(),
				usuarioRolePermissionRequest.getIdPermission()
				);
	}
	
	public static List<UsuarioRolePermission> toListUsuarioRolePermission(List<UsuarioRolePermissionRequest> usuarioRolePermissionRequest){
		List<UsuarioRolePermission> permissao = new ArrayList<>();
		
		for( UsuarioRolePermissionRequest u: usuarioRolePermissionRequest) {
			UsuarioRolePermissionId id = new UsuarioRolePermissionId(u.getIdUsuario(), u.getIdRole(), u.getIdPermission());
			Usuario usuario = new Usuario(u.getIdUsuario());
			Role role = new Role(u.getIdRole());
			Permission permission = new Permission(u.getIdPermission());
			UsuarioRolePermission urp = new UsuarioRolePermission(id,usuario,role,permission);
			permissao.add(urp);
		}
		
		return permissao; 
	}
	
	public static UsuarioRolePermissionResponse toUsuarioRolePermissionResponse(UsuarioRolePermission usuarioRolePermission) {
		return new UsuarioRolePermissionResponse(
				usuarioRolePermission.getId().getIdUsuario(),
				usuarioRolePermission.getId().getIdRole(),
				usuarioRolePermission.getId().getIdPermission(),
				usuarioRolePermission.getUsuario().getNomeUsuario(),
				usuarioRolePermission.getUsuario().getEmail(),
				usuarioRolePermission.getRole().getNomeRole().getNome(),
				usuarioRolePermission.getPermission().getNomePermission().getNomePermissions()
				);
	}

	public static List<UsuarioRolePermissionResponse> toListaUsuarioRolePermissionResponse(List<UsuarioRolePermission> lista) {
		return lista.stream()
                    .map(UsuarioRolePermissionConverter::toUsuarioRolePermissionResponse) 
                    .collect(Collectors.toList()); 
	}
	
	
}
