package com.academico.models.service.exception;

public class UserLockedException extends NegocioException {

	private static final long serialVersionUID = 3259249954242083756L;

	public UserLockedException(String message) {
		super(message);
	}

}
