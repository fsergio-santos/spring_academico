package com.academico.models.repository.impl;

import java.util.List;

import com.academico.models.model.Cidade;
import com.academico.models.service.pagination.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CidadeRepositoryCustomImpl implements CidadeRepositoryCustom {
	
    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> listaCidades() {
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT * FROM Cidade u ORDER BY u.id_cidade ");
		
		return entityManager.createNativeQuery(queryBuilder.toString(), Cidade.class)
				            .getResultList();
	}

	@Override
	public List<Cidade> buscarRegistrosPaginados(String key, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
