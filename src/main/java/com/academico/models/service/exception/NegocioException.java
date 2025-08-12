package com.academico.models.service.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -7765486624997328081L;
	
	public NegocioException(String message) {
		super(message);
	}


	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}


}
