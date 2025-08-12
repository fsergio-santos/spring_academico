package com.academico.models.service.generic;

import java.util.List;
import java.util.Map;

public interface GenericService<T, O ,ID> {
	
	public List<O> listar();
	
	public O save(T entity);
	
	public O update(ID id, T entity);
	
	public O updateField(ID id, Map<String, Object> fields );
	
	public void deleteById(ID id);
	
	public O findById(ID id);

}
