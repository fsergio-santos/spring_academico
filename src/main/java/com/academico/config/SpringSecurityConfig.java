package com.academico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.academico.models.service.components.CriptografarSenha;
import com.academico.models.service.security.JwtFilter;
import com.academico.models.service.security.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {

	@Autowired
	private CriptografarSenha crypt;

	@Autowired
	private UsuarioDetailsService userDetailsService;

	@Bean
	JwtFilter jwtTokenFilter() {
		return new JwtFilter();
	}

	@Bean
    SecurityFilterChain filterChainSecurity(HttpSecurity http) throws Exception {

    	http.csrf(csrf-> csrf.disable());
    	http.formLogin(form-> form.disable());
    	http.httpBasic(basic-> basic.disable());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.securityMatcher("/**");
		http.authorizeHttpRequests(auth -> {
		            //auth.anyRequest().permitAll()
		           auth.requestMatchers(SpringProjectConfig.WHITE_LIST_URL).permitAll();
		             
		           for (String[] rule: SpringProjectConfig.END_POINTS_WITH_AUTHENTICATION_NOT_REQUIRED ) {	
		        	   auth.requestMatchers(HttpMethod.valueOf(rule[0]), rule[1]).permitAll();
		           }; 
		           
		           
                   for (String[] rule: SpringProjectConfig.END_POINTS_WITH_ADMIN_MANAGER_USER ) {		                
                	   auth.requestMatchers(HttpMethod.valueOf(rule[0]), rule[1]).hasAnyRole("ADMIN","MANAGER","USER");
                   };
                   
		           for (String[] rule: SpringProjectConfig.END_POINTS_WITH_ADMIN_MANAGER ) {
		                auth.requestMatchers(HttpMethod.valueOf(rule[0]), rule[1]).hasAnyRole("ADMIN","MANAGER");
		           };
		           
		           for (String[] rule: SpringProjectConfig.END_POINTS_WITH_ADMIN ) {
		                auth.requestMatchers(HttpMethod.valueOf(rule[0]), rule[1]).hasRole("ADMIN");
		           };
		           
                    //.requestMatchers(HttpMethod.GET,"/logout/{email}").authenticated()
	               auth.anyRequest().authenticated();
		});
		http.cors(Customizer.withDefaults());
	
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsService).passwordEncoder(crypt.passwordEncoder());

		return builder.build();

	}

}
