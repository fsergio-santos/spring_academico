package com.academico.models.service.exception;

public class InvalidPasswordException extends NegocioException {

	private static final long serialVersionUID = 3259249954242083756L;

	public InvalidPasswordException(String message) {
		super(message);
	}

}
