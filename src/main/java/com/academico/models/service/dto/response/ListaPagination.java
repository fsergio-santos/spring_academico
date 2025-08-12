package com.academico.models.service.dto.response;

import java.util.List;

public class ListaPagination {
	
	private List<Object[]> lista;
	private Integer totalCount;
	
	public List<Object[]> getLista() {
		return lista;
	}
	public void setLista(List<Object[]> lista) {
		this.lista = lista;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
