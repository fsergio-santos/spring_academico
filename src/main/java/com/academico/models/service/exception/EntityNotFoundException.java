package com.academico.models.service.exception;

public class EntityNotFoundException extends NegocioException {

	private static final long serialVersionUID = 3259249954242083756L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
