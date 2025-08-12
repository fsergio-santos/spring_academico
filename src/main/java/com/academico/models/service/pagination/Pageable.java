package com.academico.models.service.pagination;

import com.academico.models.service.function.FormatMessage;

public class Pageable {

	private int page;
	private int pageSize;
	private int offSet;
	private int totalElements;
	private int totalPages;
	private String dir;
	private String props;
	
	
	private Pageable(int page, int pageSize) {
		
		if ( page < 0 || pageSize < 1 ) {
			throw new IllegalArgumentException(FormatMessage.formatMessage("Dados da consulta não podem ser negativos page=%s , pageSize=%s  ",page, pageSize));
		}

		this.page = page;
		this.pageSize = pageSize;
		this.offSet = ( page * pageSize);

	}
	
    private Pageable(int page, int pageSize, String dir, String props ) {
		
		if ( page < 0 || pageSize < 1 ) {
			throw new IllegalArgumentException(FormatMessage.formatMessage("Dados da consulta não podem ser negativos page=%s , pageSize=%s  ",page, pageSize));
		}

		this.page = page;
		this.pageSize = pageSize;
		this.offSet = ( page * pageSize);
		this.dir = dir.equalsIgnoreCase("desc") ? "DESC" : "ASC";
		this.props = props;

	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public int getOffSet() {
		return offSet;
	}
	
	public int getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public String getDir() {
		return dir;
	}

	public String getProps() {
		return props;
	}

	public static Pageable of (int page, int pageSize) {
		return new Pageable(page, pageSize);
	}

	public static Pageable of (int page, int pageSize, String dir, String props) {
		return new Pageable(page, pageSize, dir, props );
	}
	
}
