package com.academico.models.service.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.academico.config.SpringProjectConfig;
import com.academico.models.model.Usuario;
import com.academico.models.model.UsuarioRolePermission;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.repository.UsuarioRolePermissionRepository;
import com.academico.models.service.JwtService;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.exception.UserNotFoundException;
import com.academico.models.service.function.FormatMessage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtServiceImpl implements JwtService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    
   @Autowired
   private UsuarioRolePermissionRepository usuarioRolePermissionRepository;
    
    
    @Override
	public String getEmail(String token) {
    	return Jwts.parserBuilder()
				   .setSigningKey(getSignInKey())
				   .build()
				   .parseClaimsJws(token)
				   .getBody()
				   .getSubject();
	}
    
    
	@Override
	public Date verifyDateExpiration(String token) {
		try {
	        Claims claims = Jwts.parserBuilder()
				                .setSigningKey(getSignInKey())
				                .build()
				                .parseClaimsJws(token)
				                .getBody();
	        return claims.getExpiration();
	    } catch (MalformedJwtException e) {
	        throw new IllegalArgumentException(FormatMessage.formatMessage("Token malformado"));
	    }
	}

	@Override
	public String generateAccessToken(Usuario usuario) {
        return createToken(usuario, SpringProjectConfig.ACCESS_TOKEN_EXPIRATION);
	}
	
	@Override
	public String generateRefreshToken(Usuario usuario) {
        return createToken(usuario, SpringProjectConfig.REFRESH_TOKEN_EXPIRATION);
	}
	
	@Override
	public String registerTokenAccess(Usuario usuario) {
		return createToken(usuario, SpringProjectConfig.REGISTER_TOKEN_EXPIRATION);
	}


		
	@Override
	public boolean verifyToken(String token) {
        try {
        	Usuario usuario = usuarioRepository.findUsuarioByEmail(getEmail(token)).orElseThrow(
					() -> new UserNotFoundException(FormatMessage.formatMessage("Usuário não cadastrado")));
            String nome = getClaims(token, "nome");
    	 	return ( usuario.getNomeUsuario().equals(nome) && !verifyTokenExpired(token));
        } catch (ExpiredJwtException e) {
        	throw new NegocioException(FormatMessage.formatMessage("Token expirado!"));
        } catch (JwtException e) {
        	throw new NegocioException(FormatMessage.formatMessage("Token inválido!"));
        }
	}
	
	

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		String nome = getClaims(token, "nome");
		return (nome.equals(userDetails.getUsername())) && !verifyTokenExpired(token);
	}
	
	
	private String createToken(Usuario usuario, Long time) {
		Map<String, Object> claims = generateClaims(usuario);
        return Jwts.builder()
        		   .setClaims(claims)
        		   .setSubject(usuario.getEmail())
        		   .setIssuedAt(new Date(System.currentTimeMillis()))
        		   .setExpiration(new Date(System.currentTimeMillis() + time))
                   .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                   .compact();
    }
	
	
	private Map<String, Object> generateClaims(Usuario usuario){
		
		List<UsuarioRolePermission> listaRolePermission = usuarioRolePermissionRepository.findUsuarioRolePermissionById(usuario.getIdUsuario());
		
		List<String> roles = listaRolePermission.stream()
									.map(urp -> urp.getRole().getNomeRole().getNome()) 
									.collect(Collectors.toSet())
									.stream()
		                            .collect(Collectors.toList());
		
		List<String> permissions = listaRolePermission.stream()
									.map(urp -> urp.getPermission().getNomePermission().getNomePermissions()) 
									.collect(Collectors.toSet())
									.stream()
					                .collect(Collectors.toList());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id",usuario.getIdUsuario());
		claims.put("nome",usuario.getNomeUsuario());
        claims.put("foto", usuario.getFoto());
		claims.put("roles", roles);
		claims.put("permissions", permissions);
		
	   return claims;
	}
	
	private Boolean verifyTokenExpired(String token) {
        try {
            Date expirationDate = verifyDateExpiration(token);
            return expirationDate.before(new Date());
        } catch (JwtException e) {
            System.out.println("Erro ao verificar expiração: " + e.getMessage());
            return true; 
        }
    }
	
	
	private Key getSignInKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(SpringProjectConfig.SECRETKEY);
	    return Keys.hmacShaKeyFor(keyBytes);
	}
	
	private String getClaims(String token, String key) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
		return claims.get(key).toString();
	}




	
}
