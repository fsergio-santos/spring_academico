package com.academico.models.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import com.academico.models.model.Usuario;

public interface JwtService {
	
	 public String getEmail(String token); 

     public Date verifyDateExpiration(String token);
     
     public String generateAccessToken(Usuario usuario);
     
     public String generateRefreshToken(Usuario usuario);
     
     public String registerTokenAccess(Usuario usuario);
     
     public boolean verifyToken(String token);
     
     public boolean isTokenValid(String token, UserDetails userDetails);
	
}
