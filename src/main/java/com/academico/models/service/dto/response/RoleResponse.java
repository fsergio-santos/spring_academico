package com.academico.models.service.dto.response;

import java.util.Set;

public class RoleResponse {

	private Long idRole;
	private String nomeRole;
	private Set<PermissionResponse> permissions;

	public RoleResponse() {
		super();
	}
	
	
	

	public RoleResponse(Long idRole, String nomeRole) {
		super();
		this.idRole = idRole;
		this.nomeRole = nomeRole;
	}




	public RoleResponse(Long idRole, String nomeRole, Set<PermissionResponse> permissions) {
		super();
		this.idRole = idRole;
		this.nomeRole = nomeRole;
		this.permissions = permissions;
	}


	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}


	public Set<PermissionResponse> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<PermissionResponse> permissions) {
		this.permissions = permissions;
	}

	

}
