package com.academico.models.service.generic;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericServiceImpl<T, O, ID> implements GenericService<T, O, ID> {
 
	protected abstract JpaRepository<T, ID> getRepository();
	
	
	@Override
	public List<O> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public O save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public O update(ID id, T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public O updateField(ID id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public O findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
