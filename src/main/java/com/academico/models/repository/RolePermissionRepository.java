package com.academico.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academico.models.model.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
	
	 @Query(value = "SELECT rp FROM RolePermission rp WHERE rp.role IN :roles AND rp.target = :target")
	 List<RolePermission> findPermissions(@Param("roles") List<String> roles, @Param("target") String target);
	 
	 Optional<RolePermission> findByRoleAndTarget(@Param("role") String role, @Param("target") String target);

}
