package com.academico.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.models.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
