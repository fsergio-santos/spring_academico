package com.academico.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.models.model.Usuario;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.JwtService;
import com.academico.models.service.RefreshTokenService;
import com.academico.models.service.dto.converter.RefreshTokenConverter;
import com.academico.models.service.dto.request.RefreshTokenRequest;
import com.academico.models.service.dto.response.RefreshTokenResponse;
import com.academico.models.service.exception.AccessForbiddenException;
import com.academico.models.service.exception.UserNotFoundException;
import com.academico.models.service.function.FormatMessage;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		
	    		
	    if (!jwtService.verifyToken(refreshTokenRequest.getRefresh_token())) {
			throw new AccessForbiddenException(FormatMessage.formatMessage("Token inválido"));
		}
	    
	    
        String email = jwtService.getEmail(refreshTokenRequest.getRefresh_token());
	    
	    Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(
				() -> new UserNotFoundException(FormatMessage.formatMessage("Usuário não cadastrado")));
	    
	    String access__token = jwtService.generateAccessToken(usuario);
		String refresh_token = jwtService.generateRefreshToken(usuario);
		
		return RefreshTokenConverter.toRefreshTokenResponse(access__token, refresh_token);
	}

}
