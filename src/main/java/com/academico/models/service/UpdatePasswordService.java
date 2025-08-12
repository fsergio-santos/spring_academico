package com.academico.models.service;

import com.academico.models.service.dto.request.UpdatePasswordRequest;

public interface UpdatePasswordService {
	
	
	public void updatePassword(String email);
	
	public void changePassword(UpdatePasswordRequest  updatePasswordRequest);
	
	public boolean validateToken(String token);

}
