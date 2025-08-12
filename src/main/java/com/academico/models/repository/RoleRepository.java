package com.academico.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.models.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
