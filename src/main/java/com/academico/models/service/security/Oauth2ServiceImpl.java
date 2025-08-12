/*
 * package com.academico.models.service.security;
 * 
 * import java.time.Instant; import java.time.temporal.ChronoUnit; import
 * java.util.List; import java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.oauth2.jwt.Jwt; import
 * org.springframework.security.oauth2.jwt.JwtClaimsSet; import
 * org.springframework.security.oauth2.jwt.JwtDecoder; import
 * org.springframework.security.oauth2.jwt.JwtEncoder; import
 * org.springframework.security.oauth2.jwt.JwtEncoderParameters; import
 * org.springframework.security.oauth2.jwt.JwtException; import
 * org.springframework.stereotype.Service;
 * 
 * import com.academico.models.model.Usuario; import
 * com.academico.models.model.UsuarioRolePermission; import
 * com.academico.models.repository.UsuarioRolePermissionRepository;
 * 
 * 
 * 
 * @Service public class Oauth2ServiceImpl {
 * 
 * @Autowired private JwtEncoder jwtEncoder;
 * 
 * @Autowired private JwtDecoder jwtDecoder;
 * 
 * @Autowired private UsuarioRolePermissionRepository
 * usuarioRolePermissionRepository;
 * 
 * public String generateToken(Usuario usuario) { Instant now = Instant.now();
 * 
 * List<UsuarioRolePermission> listaRolePermission =
 * usuarioRolePermissionRepository.findUsuarioRolePermissionById(usuario.
 * getIdUsuario());
 * 
 * List<String> roles = listaRolePermission.stream() .map(urp ->
 * urp.getRole().getNomeRole().getNome()) .collect(Collectors.toSet()) .stream()
 * .collect(Collectors.toList());
 * 
 * List<String> permissions = listaRolePermission.stream() .map(urp ->
 * urp.getPermission().getNomePermission().getNomePermissions() )
 * .collect(Collectors.toSet()) .stream() .collect(Collectors.toList());
 * 
 * JwtClaimsSet claims = JwtClaimsSet.builder() .issuer("your-app")
 * .issuedAt(now) .expiresAt(now.plus(1, ChronoUnit.HOURS))
 * .subject(usuario.getNomeUsuario()) .claim("id", usuario.getIdUsuario())
 * .claim("email", usuario.getEmail()) .claim("foto", usuario.getFoto())
 * .claim("roles", roles) .claim("permissions", permissions) .build();
 * 
 * return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
 * }
 * 
 * 
 * public boolean validateToken(String token) { try { Jwt jwt =
 * jwtDecoder.decode(token); Instant now = Instant.now(); return
 * jwt.getExpiresAt() != null && jwt.getExpiresAt().isAfter(now); } catch
 * (JwtException e) { return false; } } }
 */