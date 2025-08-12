package com.academico.models.service.dto.request;

import java.util.Set;

public class RoleRequest {

	private Long idRole;
	private String nomeRole;
	private Set<PermissionRequest> permissions;

	public RoleRequest() {
	}

	public RoleRequest(Long idRole, String nomeRole) {
		this.idRole = idRole;
		this.nomeRole = nomeRole;
	}
	

	public RoleRequest(Long idRole, String nomeRole, Set<PermissionRequest> permissions) {
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

	public Set<PermissionRequest> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<PermissionRequest> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "RoleRequest [idRole=" + idRole + ", nomeRole=" + nomeRole + ", permissions=" + permissions + "]";
	}

	 

}
