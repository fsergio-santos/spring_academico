package com.academico.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.models.auditoria.AlunoHistory;

public interface AlunoHistoryRepository extends JpaRepository<AlunoHistory, Long> {

}
