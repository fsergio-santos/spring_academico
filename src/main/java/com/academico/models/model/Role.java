package com.academico.models.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.academico.models.enums.RoleName;

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
@Table(name = "ROLE")
public class Role {

	private Long idRole;
	private RoleName nomeRole;
	private Set<UsuarioRolePermission> roles;

	public Role() {
	}

	public Role(Long idRole) {
		this.idRole = idRole;
	}

	
	public Role(Long idRole, RoleName nomeRole) {
		this.idRole = idRole;
		this.nomeRole = nomeRole;
	}


	public Role(Long idRole, RoleName nomeRole, Set<UsuarioRolePermission> roles) {
		super();
		this.idRole = idRole;
		this.nomeRole = nomeRole;
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
	@SequenceGenerator(sequenceName = "sequencia_role",initialValue = 1, allocationSize = 1, name = "ROLE_SEQ")	
	@Column(name="ID_ROLE")
	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="NOME_ROLE")
	public RoleName getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(RoleName nomeRole) {
		this.nomeRole = nomeRole;
	}

	
	@OneToMany(mappedBy = "role")
	public Set<UsuarioRolePermission> getRoles() {
		return roles;
	}

	public void setRoles(Set<UsuarioRolePermission> roles) {
		this.roles = roles;
	}

	public static String getFieldNameUsingMap(String props) {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("nomeRole", "NOME_ROLE");
      
        return fieldMap.getOrDefault(props, "ID_ROLE");  
    }
}
