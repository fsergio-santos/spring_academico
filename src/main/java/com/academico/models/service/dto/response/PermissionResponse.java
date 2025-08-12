package com.academico.models.service.dto.response;

public class PermissionResponse {

	private Long idPermission;
	private String nomePermission;

	public PermissionResponse() {

	}

	public PermissionResponse(Long idPermission, String nomePermission) {
		this.idPermission = idPermission;
		this.nomePermission = nomePermission;
	}

	public Long getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}

	public String getNomePermission() {
		return nomePermission;
	}

	public void setNomePermission(String nomePermission) {
		this.nomePermission = nomePermission;
	}

}
