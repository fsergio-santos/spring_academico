package com.academico.models.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO_ROLE_PERMISSION")
public class UsuarioRolePermission {

	private UsuarioRolePermissionId id;

	private Usuario usuario;
	private Role role;
	private Permission permission;
	
	public UsuarioRolePermission() {
		super();
	}
	
	public UsuarioRolePermission(UsuarioRolePermissionId id) {
		this.id = id;
	}

	public UsuarioRolePermission(UsuarioRolePermissionId id, Usuario usuario, Role role, Permission permission) {
		this.id = id;
		this.usuario = usuario;
		this.role = role;
		this.permission = permission;
	}

	@EmbeddedId
	public UsuarioRolePermissionId getId() {
		return id;
	}

	public void setId(UsuarioRolePermissionId id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne
	@MapsId("idUsuario")
	@JoinColumn(name = "id_usuario", nullable=false,insertable=false, updatable=false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@JsonIgnore
	@ManyToOne
	@MapsId("idRole")
	@JoinColumn(name = "id_role", nullable=false,insertable=false, updatable=false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	@ManyToOne
	@MapsId("idPermission")
	@JoinColumn(name = "id_permission", nullable=false,insertable=false, updatable=false)
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, permission, role, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRolePermission other = (UsuarioRolePermission) obj;
		return Objects.equals(id, other.id) && Objects.equals(permission, other.permission)
				&& Objects.equals(role, other.role) && Objects.equals(usuario, other.usuario);
	}
	
	

}
