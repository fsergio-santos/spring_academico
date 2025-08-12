package com.academico.models.model;

import java.util.Set;

import com.academico.models.enums.PermissionsName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="PERMISSION")
public class Permission {

	private Long idPermission;
	private PermissionsName nomePermission;
	private Set<UsuarioRolePermission> usuarioRolesPermission; 

	public Permission() {

	}

	public Permission(Long idPermission) {
		this.idPermission = idPermission;
	}
	
	public Permission(Long idPermission, PermissionsName nomePermission) {
		this.idPermission = idPermission;
		this.nomePermission = nomePermission;
	}
	
	

	public Permission(Long idPermission, PermissionsName nomePermission, Set<UsuarioRolePermission> usuarioRolesPermission) {
		super();
		this.idPermission = idPermission;
		this.nomePermission = nomePermission;
		this.usuarioRolesPermission = usuarioRolesPermission;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_SEQ")
	@SequenceGenerator(sequenceName = "sequencia_permission",initialValue = 1, allocationSize = 1, name = "PERMISSION_SEQ")	
	@Column(name="ID_PERMISSION")
	public Long getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="NOME_PERMISSION")
	public PermissionsName getNomePermission() {
		return nomePermission;
	}

	public void setNomePermission(PermissionsName nomePermission) {
		this.nomePermission = nomePermission;
	}

	@OneToMany(mappedBy = "permission")
	public Set<UsuarioRolePermission> getUsuarioRolesPermission() {
		return usuarioRolesPermission;
	}

	public void setUsuarioRolesPermission(Set<UsuarioRolePermission> usuarioRolesPermission) {
		this.usuarioRolesPermission = usuarioRolesPermission;
	}
	
	
	

}
