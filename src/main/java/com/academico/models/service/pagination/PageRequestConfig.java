package com.academico.models.service.pagination;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.academico.models.service.function.FormatMessage;

public class PageRequestConfig {

	public static Pageable gerarPagina(Integer page, Integer pageSize, String dir, String props) {
		if (Objects.isNull(page) || Objects.isNull(pageSize) || Objects.isNull(dir) || Objects.isNull(props)) {
	        throw new IllegalArgumentException(
	                FormatMessage.formatMessage("Dados para consulta 'paginação' foram passados incorretamente."));
	    }
		return PageRequest.of(page, pageSize, getSortDirection(dir, props));
	}
	
	public static Pageable gerarPagina(Integer page, Integer pageSize) {
		if (Objects.isNull(page) || Objects.isNull(pageSize)) {
	        throw new IllegalArgumentException(
	                FormatMessage.formatMessage("Dados para consulta 'paginação'foram passados incorretamente."));
	    }
		return PageRequest.of(page, pageSize);
	}
	
	private static Sort getSortDirection(String dir, String props) {
		Sort sort = dir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				  ? Sort.by(props).ascending()
				  : Sort.by(props).descending();
		return sort;
	}
}



