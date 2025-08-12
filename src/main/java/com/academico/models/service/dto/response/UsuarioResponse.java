package com.academico.models.service.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta dos dados do usuário")
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> implements Serializable {

	private static final long serialVersionUID = 5943541013597691831L;

	private Long    idUsuario;
	private String  codUsuario;
	private String  nomeUsuario;
	private String  email;
	//private String  senha;
	private String  foto;
	private Integer tipo;
	//private String confirmSenha;
	private Long idCidade;
	private Long idAluno;
	private String codAluno;
	private String nomeAluno;
	private Integer idade;
	private Long idProfessor;
	private String codProfessor;
	private String nomeProfessor;
	//private Integer rowNum;
	private String nome_cidade;
	private boolean ativo;
	private Set<RoleResponse> roles;

	public UsuarioResponse() {
		super();
	}

	public UsuarioResponse(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			String senha,
			Integer tipo,
			String confirmSenha,
			Long idCidade, 
			Long idAluno,
			String codAluno, 
			String  nomeAluno, 
			Integer idade,
			Long    idProfessor,
			String  codProfessor,
			String  nomeProfessor,
			String  foto, 
			boolean ativo
		) {

		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		//this.senha = senha;
		this.tipo = tipo;
		//this.confirmSenha=confirmSenha;
		this.idCidade = idCidade;
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.idade = idade;
		this.idProfessor = idProfessor;
		this.codProfessor = codProfessor;
		this.nomeProfessor = nomeProfessor;
		this.foto = foto;
		this.ativo = ativo;
	}

	
	/* o método construtor está sendo usando para listagem com paginação no cliente */
	public UsuarioResponse(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			Integer tipo,
			String nome_cidade,
			String foto,
			Boolean ativo
		) {
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipo = tipo;
		this.nome_cidade = nome_cidade;
		this.foto = foto;
		this.ativo = ativo;
	}

	/* o métdo está sendo usando na paginação no servidor */
	public UsuarioResponse(BigDecimal id, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			String senha, 
			Boolean ativo,
			String foto, 
			BigDecimal tipo,
			BigDecimal idCidade, 
			BigDecimal rowNum, 
			String nome_cidade ) {
		this.idUsuario = id.longValue();
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipo = tipo == null ? 0 : tipo.intValue();
		this.idCidade = idCidade == null ? 1L : idCidade.longValue();
		this.foto = foto;
		//this.senha = "";
		this.ativo = ativo;
		//this.rowNum = rowNum.intValue();
		this.nome_cidade = nome_cidade;
		
	}
	
	public UsuarioResponse(
			Long idUsuario, 
			String nomeUsuario, 
			String email,
			Set<RoleResponse> roles
		) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.roles = roles;
		
	}
	
	@Schema(example = "1")
	public Long getIdUsuario() {
		return idUsuario;
	}


	@Schema(example = "XXX-999999")
	public String getCodUsuario() {
		return codUsuario;
	}


	@Schema(example = " Antônio Vieira da Silva")
	public String getNomeUsuario() {
		return nomeUsuario;
	}

    @Schema(example = " antonio@gmail.com")
	public String getEmail() {
		return email;
	}


    @Schema(example = " 1 - Professor, 2 - Aluno ")
	public Integer getTipo() {
		return tipo;
	}

    @Schema(example = "1")
	public Long getIdCidade() {
		return idCidade;
	}

	@Schema(example = "XXX-999999")
	public String getCodAluno() {
		return codAluno;
	}

	@Schema(example = " Antônio Vieira da Silva")
	public String getNomeAluno() {
		return nomeAluno;
	}

	@Schema(example = "18")
	public Integer getIdade() {
		return idade;
	}

	@Schema(example = "XXX-999999")
	public String getCodProfessor() {
		return codProfessor;
	}

	@Schema(example = " Antônio Vieira da Silva")
	public String getNomeProfessor() {
		return nomeProfessor;
	}


	@Schema(example = "foto.jpg")
	public String getFoto() {
		return foto;
	}

	public boolean isAtivo() {
		return ativo;
	}

	//public String getSenha() {
	//	return senha;
	//}

	//public Integer getRowNum() {
	//	return rowNum;
	//}

	@Schema(example = "Birigui")
	public String getNome_cidade() {
		return nome_cidade;
	}

	
	@Schema(example = "1")
	public Long getIdAluno() {
		return idAluno;
	}

	@Schema(example = "1")
	public Long getIdProfessor() {
		return idProfessor;
	}

	//public String getConfirmSenha() {
	//	return confirmSenha;
	//}
	
	@Schema(example = "ADM")
	public Set<RoleResponse> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "UsuarioResponse [idUsuario=" + idUsuario + ", codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", email=" + email + "]";
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//public void setSenha(String senha) {
	//	this.senha = senha;
	//}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	//public void setConfirmSenha(String confirmSenha) {
	//	this.confirmSenha = confirmSenha;
	//}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}

	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	//public void setRowNum(Integer rowNum) {
	//	this.rowNum = rowNum;
	//}

	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setRoles(Set<RoleResponse> roles) {
		this.roles = roles;
	}
	
	

}
