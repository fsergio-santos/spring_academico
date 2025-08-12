package com.academico.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academico.models.model.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	@Query(value = "SELECT * FROM ("
    		+ "SELECT a.*, ROW_NUMBER() OVER "
    		+ "("
    		+ " ORDER BY a.id_aluno"
    		+ ") "
    		+ " AS rn FROM aluno a) "
		    + " WHERE rn > :offset AND rn <= (:offset + :limit)", 
	       nativeQuery = true)
	List<Aluno> findAlunoPaginados(@Param("offset") int offset, 
            						@Param("limit") int limit );


    @Query(value = "SELECT COUNT(*) FROM Aluno ", nativeQuery = true)
	int countAlunos();
	
    @Query(value = "SELECT COUNT(*) FROM Aluno " +
            "WHERE nome_aluno LIKE '%' || :key || '%' " +
            "   OR cod_aluno LIKE '%' || :key || '%'", nativeQuery = true)
	int countAlunosByKey(@Param("key") String key);

}
