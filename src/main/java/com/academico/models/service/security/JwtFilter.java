package com.academico.models.service.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.academico.config.SpringProjectConfig;
import com.academico.models.service.JwtService;
import com.academico.models.service.exception.AccessForbiddenException;
import com.academico.models.service.function.FormatMessage;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		String jwt = null;
		String email = null;
		String jwtToken = null;

		if (request.getServletPath().contains("/rest/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
		    filterChain.doFilter(request, response);
		    return;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			filterChain.doFilter(request, response);
			return;
		}

		for (Cookie cookie : cookies) {
			if (SpringProjectConfig.ACCESS_TOKEN.equals(cookie.getName())) {
				jwtToken = cookie.getValue();
				break;
			}
		}

		jwt = authHeader.substring(7);

		if (jwtToken == null) {
			filterChain.doFilter(request, response);
			return;
		}

		//jwt = jwtToken.substring(7);

		email = jwtService.getEmail(jwt);

		try {
			if (!jwtService.verifyToken(jwt)) {
				throw new AccessForbiddenException(FormatMessage.formatMessage("Token inválido"));
			}
			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
				if (jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			throw new AccessForbiddenException(FormatMessage.formatMessage("Token inválido"));
		}

	}

}
