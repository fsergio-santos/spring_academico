package com.academico.models.service.exception;

public class UserNotFoundException extends NegocioException {

	private static final long serialVersionUID = 5404508385461277412L;

	public UserNotFoundException(String message) {
		super(message);
		
	}

}
