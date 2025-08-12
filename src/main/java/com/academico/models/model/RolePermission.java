package com.academico.models.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE_PERMISSION")
public class RolePermission {
	
    private Long   idRolePermission;
	private String role;
	private String target; 
	private Integer read;
	private Integer create;
	private Integer update;
	private Integer delete;

	
	public RolePermission() {
		super();
	}


	public RolePermission(Long idRolePermission, String role, String target, Integer read, Integer create, Integer update,
			Integer delete) {
		super();
		this.idRolePermission = idRolePermission;
		this.role = role;
		this.target = target;
		this.read = read;
		this.create = create;
		this.update = update;
		this.delete = delete;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_PERMISSION_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCIA_ROLE_PERMISSION",initialValue = 1, allocationSize = 1, name = "ROLE_PERMISSION_SEQ")
	@Column(name = "ID_ROLE_PERMISSION")
	public Long getIdRolePermission() {
		return idRolePermission;
	}



	public void setIdRolePermission(Long idRolePermission) {
		this.idRolePermission  = idRolePermission;
	}


	@Column(name = "ROLE")
	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}


	@Column(name = "TARGET")
	public String getTarget() {
		return target;
	}



	public void setTarget(String target) {
		this.target = target;
	}


    @Column(name = "READ_PERMISSION")
	public Integer getRead() {
		return read;
	}



	public void setRead(Integer read) {
		this.read = read;
	}


    @Column(name="CREATE_PERMISSION")
	public Integer getCreate() {
		return create;
	}



	public void setCreate(Integer create) {
		this.create = create;
	}


    @Column(name="UPDATE_PERMISSION") 
	public Integer getUpdate() {
		return update;
				
	}



	public void setUpdate(Integer update) {
    	this.update= update;
	}


	@Column(name="DELETE_PERMISSION") 
	public Integer getDelete() {
		return delete;
	}



	public void setDelete(Integer delete) {
		this.delete = delete;
	}



	@Override
	public int hashCode() {
		return Objects.hash(idRolePermission);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermission other = (RolePermission) obj;
		return Objects.equals(idRolePermission, other.idRolePermission);
	}
	
	
	
	
	

}
