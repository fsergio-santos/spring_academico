package com.academico.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academico.models.model.Cidade;
import com.academico.models.repository.impl.CidadeRepositoryCustom;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryCustom {
	
	
	@Query(value="SELECT c FROM Cidade c WHERE LOWER(c.nomeCidade) LIKE LOWER(CONCAT('%', :nome, '%')) ")
	List<Cidade> buscarCidadePorNome(@Param("nome") String nome);

	@Query(value = "SELECT COUNT(*) FROM Cidade ", nativeQuery = true)
	int countCidades();

	@Query(value = "SELECT COUNT(*) FROM Cidade " +
	            "WHERE nome_cidade LIKE '%' || :key || '%' " +
	            "   OR cod_cidade LIKE '%' || :key || '%'", nativeQuery = true) 
	int countCidadesByKey(@Param("key") String key);
	
	@Query(value = "SELECT * FROM ("
    		+ "SELECT c.*, ROW_NUMBER() OVER "
    		+ "("
    		+ " ORDER BY c.id_cidade"
    		+ ") "
    		+ " AS rn FROM Cidade c) "
		    + " WHERE rn > :offset AND rn <= (:offset + :limit)", 
	       nativeQuery = true)
	List<Cidade> findCidadePaginados(@Param("offset") int offset, @Param("limit") int limit );
	
	
    @Query(value="SELECT c FROM Cidade c "
         		+"WHERE c.idCidade =:idCidade ")
    Optional<Cidade> findByidCidade(@Param("idCidade") Long idCidade);

}
