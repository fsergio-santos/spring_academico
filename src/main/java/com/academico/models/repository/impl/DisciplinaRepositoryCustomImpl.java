package com.academico.models.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.academico.models.service.pagination.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
public class DisciplinaRepositoryCustomImpl implements DisciplinaRepositoryCustom {
	
    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> buscarRegistrosPaginados(String key, Pageable pageable) {
		
		
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT * FROM ( ");
		queryBuilder.append("SELECT d.id_disciplina, d.cod_disciplina, d.nome_disciplina, d.id_professor,  ");
		queryBuilder.append("p.nome_professor AS nome_professor,  ROWNUM rnum ");
	    queryBuilder.append("FROM Disciplina d ");
	    queryBuilder.append("LEFT JOIN Professor p ON d.id_professor = p.id_professor "); 
	    queryBuilder.append("WHERE LOWER(d.cod_disciplina) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("OR LOWER(d.nome_disciplina) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("ORDER BY d."+pageable.getProps()+" ").append(pageable.getDir());
		queryBuilder.append(") ");
		queryBuilder.append("WHERE rnum > :startRow AND rnum <= :endRow");
	 	
	    Query query = entityManager.createNativeQuery(queryBuilder.toString());
	    
	    query.setParameter("key", key);
	    query.setParameter("startRow", pageable.getOffSet());
	    query.setParameter("endRow", pageable.getPageSize());

	    List<Object[]> lista = query.getResultList();
	    
	    return lista;
		
	}

	@Override
	public Long totalRegistrosDisciplina(String key ) {
        
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT count(*) FROM ");
	    queryBuilder.append("Disciplina d ");
	    queryBuilder.append("LEFT JOIN Professor p ON d.id_professor = p.id_professor "); 
	    queryBuilder.append("WHERE LOWER(d.cod_disciplina) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("OR LOWER(d.nome_disciplina) LIKE LOWER('%' || :key || '%') ");
		
	    Query query = entityManager.createNativeQuery(queryBuilder.toString());
	    
	    query.setParameter("key", key);
	    
	    BigDecimal total = (BigDecimal) query.getSingleResult();
	    
	    return total.longValue();
	}

}
