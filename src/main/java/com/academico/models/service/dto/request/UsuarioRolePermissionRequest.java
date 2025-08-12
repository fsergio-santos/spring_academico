package com.academico.models.service.dto.request;

public class UsuarioRolePermissionRequest {
	
	private Long idUsuario;
	private Long idRole;
	private Long idPermission;
	
	
	
	public UsuarioRolePermissionRequest() {
		super();
	}

	public UsuarioRolePermissionRequest(Long idUsuario, Long idRole, Long idPermission) {
		super();
		this.idUsuario = idUsuario;
		this.idRole = idRole;
		this.idPermission = idPermission;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Long getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}

	@Override
	public String toString() {
		return "UsuarioRolePermissionRequest [idUsuario=" + idUsuario + ", idRole=" + idRole + ", idPermission="
				+ idPermission + "]";
	}
	
	

}
