package com.academico.models.service.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LoginResponse {

	private Long   idUsuario;
	private String nomeUsuario;
	private String email;
	private String foto;
	private String access_token;
	private String refresh_token;
	//private List<List<Long>> usuarios;
	//private Set<UsuarioRolePermission> usuarios;
	private List<String> roles;
	private List<String> permissions;

	public LoginResponse() {

	}


	public Long getIdUsuario() {
		return idUsuario;
	}

	public LoginResponse(Long idUsuario, String nomeUsuario, String email, String foto, String access_token,
			String refresh_token, List<String> roles, List<String> permissions) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.foto = foto;
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.roles = roles;
		this.permissions = permissions;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public String getFoto() {
		return foto;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String accessToken) {
		this.access_token = accessToken;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refreshToken) {
		this.refresh_token = refreshToken;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<String> getRoles() {
		return roles;
	}


	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<String> getPermissions() {
		return permissions;
	}

	
	
	

}
