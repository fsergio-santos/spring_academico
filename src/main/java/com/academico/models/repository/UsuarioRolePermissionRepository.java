package com.academico.models.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academico.models.model.Usuario;
import com.academico.models.model.UsuarioRolePermission;
import com.academico.models.model.UsuarioRolePermissionId;

public interface UsuarioRolePermissionRepository extends JpaRepository<UsuarioRolePermission, UsuarioRolePermissionId> {
	
	
	Set<UsuarioRolePermission> findByUsuario(Usuario usuario);
	
	
	@Query(value="SELECT urp FROM UsuarioRolePermission urp WHERE urp.usuario.idUsuario =:idUsuario ")
	List<UsuarioRolePermission> findUsuarioRolePermissionById(@Param("idUsuario") Long idUsuraio);

}
