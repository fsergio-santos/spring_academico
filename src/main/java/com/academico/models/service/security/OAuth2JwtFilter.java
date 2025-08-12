/*
 * package com.academico.models.service.security;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import jakarta.servlet.FilterChain; import jakarta.servlet.ServletException;
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @SuppressWarnings("unused") public class OAuth2JwtFilter extends
 * OncePerRequestFilter {
 * 
 * 
 * @Override protected void doFilterInternal( HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException {
 * 
 * final String authHeader = request.getHeader("Authorization");
 * 
 * String jwt = null;
 * 
 * if (request.getServletPath().contains("/rest/login")) {
 * filterChain.doFilter(request, response); return; }
 * 
 * if (authHeader == null || !authHeader.startsWith("Bearer ")) {
 * filterChain.doFilter(request, response); return; }
 * 
 * jwt = authHeader.substring(7); }
 * 
 * 
 * 
 * }
 */