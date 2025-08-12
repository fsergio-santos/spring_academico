package com.academico.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.models.auditoria.UsuarioHistory;

public interface UsuarioHistoryRepository extends JpaRepository<UsuarioHistory, Long>{

}
