package com.academico.models.service.dto.response;

public class UsuarioRolePermissionResponse {

	private Long idUsuario;
	private Long idRole;
	private Long idPermission;
	private String nomeUsuario;
	private String email;
	private String nomeRole;
	private String nomePermission;

	public UsuarioRolePermissionResponse() {
	}

	public UsuarioRolePermissionResponse(
			Long idUsuario, 
			Long idRole, 
			Long idPermission, 
			String nomeUsuario,
			String email,
			String nomeRole, 
			String nomePermission) {
		super();
		this.idUsuario = idUsuario;
		this.idRole = idRole;
		this.idPermission = idPermission;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.nomeRole = nomeRole;
		this.nomePermission = nomePermission;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public String getNomePermission() {
		return nomePermission;
	}

	public void setNomePermission(String nomePermission) {
		this.nomePermission = nomePermission;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

}
