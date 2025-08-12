package com.academico.models.service.dto.request;

import java.util.Set;

import com.academico.models.service.dto.validation.UsuarioValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@UsuarioValidation
@Schema(description = "Requisição dos dados do usuário")
public class UsuarioRequest {

	private Long idUsuario;
	private String codUsuario;
	private String nomeUsuario;
	private String email;
//	private String senha;
//	private String confirmSenha;
	private String foto;
	private Integer tipo;
	private Long idCidade;
	private Long idAluno;
	private String codAluno;
	private String nomeAluno;
	private Integer idade;
	private Long idProfessor;
	private String codProfessor;
	private String nomeProfessor;
	private boolean ativo;
	private Set<RoleRequest> roles;

	public UsuarioRequest() {
	}

	

	public UsuarioRequest(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
//			String senha,
//			String confirmSenha, 
			String foto, 
			Integer tipo, 
			Long idCidade, 
			Long idAluno, 
			String codAluno,
			String nomeAluno, 
			Integer idade, 
			Long idProfessor, 
			String codProfessor, 
			String nomeProfessor,
			boolean ativo,
			Set<RoleRequest> roles
			
		) {
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
//		this.senha = senha;
//		this.confirmSenha = confirmSenha;
		this.foto = foto;
		this.tipo = tipo;
		this.idCidade = idCidade;
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.idade = idade;
		this.idProfessor = idProfessor;
		this.codProfessor = codProfessor;
		this.nomeProfessor = nomeProfessor;
		this.ativo = ativo;
		this.roles = roles;
	}


	@Schema(example = "1")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Schema(example = "XXX-999999")
	@NotBlank(message = "O código do usuário é obrigatório.")
	@Size(min = 5, max = 20, message = "O código do usuário deve ter entre 5 e 20 caracteres.")
	@Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{S}]+$", message = "O código do usuário deve conter apenas letras e números.")
	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	@Schema(example = " Antônio Vieira da Silva")
	@NotBlank(message = "O nome do usuário é obrigatório.")
	@Size(min = 5, max = 100, message = "O nome do usuário deve ter entre 5 e 100 caracteres.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\p{P}\\p{S}\\p{C}])[\\p{L}\\p{N}\\p{P}\\p{S}\\p{C}]+$", message = "A string deve conter letras maiúsculas, minúsculas e caracteres especiais.")
    private String suaString;
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Schema(example = " antonio@gmail.com")
	@NotBlank(message = "{NotBlank.usuarioRequest.email_requerido}")
	@Size(min = 5, max = 100, message = "{Size.usuarioRequest.email_length}")
	@Email(message = "{Email.usuarioRequest.email_invalido}")	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	@NotBlank(message = "A senha é obrigatória.")
//	@Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
//	@Pattern(
//	    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
//	    message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
//	)
//	public String getSenha() {
//		return senha;
//	}
//
//	
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//
//	@NotBlank(message = "A senha é obrigatória.")
//	@Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
//	@Pattern(
//	    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
//	    message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
//	)
//	public String getConfirmSenha() {
//		return confirmSenha;
//	}
//
//	public void setConfirmSenha(String confirmSenha) {
//		this.confirmSenha = confirmSenha;
//	}

	@Schema(example = "foto.jpg")
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	@Schema(description = "Tipo de usuário: 1 (Professor), 2 (Aluno)", allowableValues = {"1", "2"}, example = " 1 - Professor, 2 - Aluno ") 
	@NotNull(message = "O tipo do usuário é obrigatório.")
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Schema(example = "1")
	@NotBlank(message = "O nome da cidade é obrigatório.")
	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}
	
	
	@Schema(example = "1")
	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}


	@Schema(example = "1")
	public String getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}

	@Schema(example = " Antônio Vieira da Silva")
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	@Schema(example = "18")
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	
	@Schema(example = "1")
	public Long getIdProfessor() {
		return idProfessor;
	}



	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}


	@Schema(example = "XXX-999999")
	public String getCodProfessor() {
		return codProfessor;
	}

	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}

	@Schema(example = " Antônio Vieira da Silva")
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	
	@Schema(example = "ADM")
	public Set<RoleRequest> getRoles() {
		return roles;
	}



	@Override
	public String toString() {
		return "UsuarioRequest [idUsuario=" + idUsuario + ", codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", email=" + email + ", foto=" + foto
				+ ", tipo=" + tipo + ", idCidade=" + idCidade + ", idAluno=" + idAluno + ", codAluno=" + codAluno
				+ ", nomeAluno=" + nomeAluno + ", idade=" + idade + ", idProfessor=" + idProfessor + ", codProfessor="
				+ codProfessor + ", nomeProfessor=" + nomeProfessor + ", ativo=" + ativo + ", roles=" + roles + "]";
	}





}
