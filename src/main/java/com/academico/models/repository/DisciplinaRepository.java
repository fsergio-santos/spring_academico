package com.academico.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.models.model.Disciplina;
import com.academico.models.repository.impl.DisciplinaRepositoryCustom;
import com.academico.models.repository.projection.DisciplinaProfessorProjection;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>, DisciplinaRepositoryCustom {

	@Query(value = "SELECT COUNT(*) FROM Usuario ", nativeQuery = true)
	int countDisciplinas();
	
	Optional<Disciplina> findByidDisciplina(Long id);

	 @Query("SELECT "
	    		+ "d.idDisciplina AS idDisciplina, "
	    		+ "d.codDisciplina AS codDisciplina, "
	    		+ "d.nomeDisciplina AS nomeDisciplina, "
	    		+" p.nomeProfessor AS nomeProfessor "
	    		+ "FROM Disciplina d "
	    		+ "LEFT JOIN d.professor p ")
	List<DisciplinaProfessorProjection> listagemDisciplina();



}
