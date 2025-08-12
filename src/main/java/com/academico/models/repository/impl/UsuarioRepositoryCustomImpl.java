package com.academico.models.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.academico.models.service.dto.response.ListaPagination;
import com.academico.models.service.pagination.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;


@Repository
public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {
	
    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public ListaPagination buscarRegistrosPaginados(String key, Pageable pageable) {
		
		
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT * FROM ( ");
		queryBuilder.append("SELECT u.id_usuario, u.cod_usuario, u.nome_usuario, u.email, u.tipo, ");
		queryBuilder.append("u.id_cidade, u.foto, u.senha, u.ativo, c.nome_cidade AS nome_cidade,  ROWNUM rnum ");
	    queryBuilder.append("FROM Usuario u ");
	    queryBuilder.append("LEFT JOIN Cidade c ON u.id_cidade = c.id_cidade "); 
	    queryBuilder.append("WHERE LOWER(u.cod_usuario) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("OR LOWER(u.nome_usuario) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("OR LOWER(u.email) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("ORDER BY u."+pageable.getProps()+" ").append(pageable.getDir());
		queryBuilder.append(") ");
		queryBuilder.append("WHERE rnum > :startRow AND rnum <= :endRow");
	 	
	    Query query = entityManager.createNativeQuery(queryBuilder.toString());
	    
	    query.setParameter("key", key);
	    query.setParameter("startRow", pageable.getOffSet());
	    query.setParameter("endRow", pageable.getPageSize());

	    List<Object[]> lista = query.getResultList();
	    
	    int totalCount = totalRegistrosUsuarios(key).intValue();
	    
	    ListaPagination pagination = new ListaPagination();
    	
    	pagination.setLista(lista);
    	
    	pagination.setTotalCount(totalCount);
                
        return pagination;
		
	}

	@Override
	public Long totalRegistrosUsuarios(String key ) {
        
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT count(*) FROM ");
	    queryBuilder.append("Usuario u ");
	    queryBuilder.append("LEFT JOIN Cidade c ON u.id_cidade = c.id_cidade "); 
	    queryBuilder.append("WHERE LOWER(u.cod_usuario) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("OR LOWER(u.nome_usuario) LIKE LOWER('%' || :key || '%') ");
		queryBuilder.append("OR LOWER(u.email) LIKE LOWER('%' || :key || '%') ");
		
	    Query query = entityManager.createNativeQuery(queryBuilder.toString());
	    
	    query.setParameter("key", key);
	    
	    BigDecimal total = (BigDecimal) query.getSingleResult();
	    
	    return total.longValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ListaPagination getUsuarioPagination(String key, Pageable pageable) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("USER_PAGINATION")
		.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_page_size", Integer.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_sort_column", String.class , ParameterMode.IN)
		.registerStoredProcedureParameter("p_sort_direction", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_search_key", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_result", void.class, ParameterMode.REF_CURSOR)
		.registerStoredProcedureParameter("p_total_count", Integer.class, ParameterMode.OUT)
		.setParameter("p_page", pageable.getPage())
		.setParameter("p_page_size", pageable.getPageSize())
		.setParameter("p_sort_column", pageable.getProps())
		.setParameter("p_sort_direction", pageable.getDir())
		.setParameter("p_search_key", key);
		
		
		List<Object[]> lista = query.getResultList();
		
    	Integer totalCount = (Integer) query.getOutputParameterValue("p_total_count");
    	
    	ListaPagination pagination = new ListaPagination();
    	
    	pagination.setLista(lista);
    	
    	pagination.setTotalCount(totalCount);
        
        
        return pagination;
        
    
	}

}
