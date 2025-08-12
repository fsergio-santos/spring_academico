package com.academico.models.service.dto.response;

public class RolePermissionResponse {
	
	private Long   idRolePermission;
	private String role;
	private String target; 
	private Integer read;
	private Integer create;
	private Integer update;
	private Integer delete;
	
	public RolePermissionResponse() {

	}

	public RolePermissionResponse(
			Long idRolePermission, 
			String role, 
			String target, 
			Integer read, 
			Integer create, 
			Integer update,
			Integer delete) {
		this.idRolePermission = idRolePermission;
		this.role = role;
		this.target = target;
		this.read = read;
		this.create = create;
		this.update = update;
		this.delete = delete;
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
	public Integer isREAD() {
		return read;
	}
	public Integer getCreate() {
		return create;
	}
	public Integer getUpdate() {
		return update;
	}
	public Integer getDelete() {
		return delete;
	}
	
	
	

}
