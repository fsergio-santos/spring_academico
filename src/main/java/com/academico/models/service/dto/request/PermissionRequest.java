package com.academico.models.service.dto.request;

public class PermissionRequest {

	private Long idPermission;
	private String nomePermission;

	public PermissionRequest() {

	}

	public PermissionRequest(Long idPermission, String nomePermission) {
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

	@Override
	public String toString() {
		return "PermissionRequest [idPermission=" + idPermission + ", nomePermission=" + nomePermission + "]";
	}
	
	

}
