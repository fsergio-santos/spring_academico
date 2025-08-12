package com.academico.models.service.dto.request;

public class RolePermissionRequest {
	
	private Long   idRolePermission;
	private String role;
	private String target; 
	private Integer read;
	private Integer create;
	private Integer update;
	private Integer delete;
	
	public RolePermissionRequest() {
		super();
	}

	public RolePermissionRequest(Long idRolePermission, String role, String target, Integer read, Integer create,
			Integer update, Integer delete) {
		super();
		this.idRolePermission = idRolePermission;
		this.role = role;
		this.target = target;
		this.read = read;
		this.create = create;
		this.update = update;
		this.delete = delete;
	}

	public Integer getUpdate() {
		return update;
	}

	public void setUpdate(Integer update) {
		this.update = update;
	}

	public Long getIdRolePermission() {
		return idRolePermission;
	}

	public String getRole() {
		return role;
	}

	public String getTarget() {
		return target;
	}

	public Integer getRead() {
		return read;
	}

	public Integer getCreate() {
		return create;
	}

	public Integer getDelete() {
		return delete;
	}
	
	
	
	
	

}
