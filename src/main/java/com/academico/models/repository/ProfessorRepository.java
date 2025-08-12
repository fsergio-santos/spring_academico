package com.academico.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academico.models.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,Long>{

    @Query(value = "SELECT * FROM ("
    		+ "SELECT pu.*, ROW_NUMBER() OVER "
    		+ "("
    		+ " ORDER BY p.id_professor"
    		+ ") "
    		+ " AS rn FROM professor p) "
		    + " WHERE rn > :offset AND rn <= (:offset + :limit)", 
	       nativeQuery = true)
	List<Professor> findProfessorPaginados(@Param("offset") int offset,@Param("limit") int limit );

	 @Query(value = "SELECT COUNT(*) FROM Professor ", nativeQuery = true)
	int countProfessors();

	@Query(value = "SELECT COUNT(*) FROM Usuario " +
               "WHERE nome_professor LIKE '%' || :key || '%' " +
               "   OR cod_professor LIKE '%' || :key || '%'", nativeQuery = true)
	int countProfessorsByKey(@Param("key") String key);

}
