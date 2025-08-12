package com.academico.models.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.academico.models.service.listener.UsuarioServiceListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@EntityListeners(UsuarioServiceListener.class)
@SQLDelete(sql="UPDATE USUARIO SET DELETED = 1 WHERE ID_USUARIO = ?  ")
@SQLRestriction("DELETED = 1")
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long   idUsuario;
	private String codUsuario;
	private String nomeUsuario;
	private String email;
	private String senha;
	private String foto;
	private Integer tipo;
	private String otp;
	private LocalDateTime otpExpiry;
	private Cidade cidade;
	private boolean ativo = true;
	private Professor professor;
	private Aluno     aluno;
	private Integer   softDelete = 0;
	
	private Set<UsuarioRolePermission> usuarios;
	

	public Usuario() {

	}
	
	public Usuario(Long idUsuario) {
      this.idUsuario = idUsuario;
	}
	
	public Usuario(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			String senha,
			String foto, 
			Integer tipo, 
			String otp,
			LocalDateTime otpExpiry,
			boolean ativo
		) {
		
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.tipo = tipo;
		this.otp = otp;
		this.otpExpiry = otpExpiry;
        this.ativo = ativo;
		
	}

	
	public Usuario(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			String senha,
			String foto, 
			Integer tipo, 
			boolean ativo
		) {
		
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.tipo = tipo;
        this.ativo = ativo;
		
	}
	
	
	public Usuario(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			Integer tipo 
		) {
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipo = tipo;
	}
	
	
	public Usuario(
			String nomeUsuario,
			String email,
			String senha
		) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
	}
	
	
	public Usuario(String otp) {
		this.otp = otp;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCIA_USUARIO",initialValue = 1, allocationSize = 1, name = "USUARIO_SEQ")
	@Column(name = "ID_USUARIO")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(name = "COD_USUARIO")
	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	@Column( name = "NOME_USUARIO" )
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="TIPO", nullable = false)
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Column(name="SENHA")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name="FOTO")
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	@Column(name="ATIVO")
	public boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIDADE")
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToOne(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	@Column(name="OTP")
	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Column(name="OTP_EXPIRY")
	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}


	public void setOtpExpiry(LocalDateTime otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	

	@Column(name="DELETED")
	public Integer getSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(Integer softDelete) {
		this.softDelete = softDelete;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", email=" + email + ",  foto=" + foto + ", tipo=" + tipo + ", otp=" + otp
				+ ", otpExpiry=" + otpExpiry + ", cidade=" + cidade + ", ativo=" + ativo + "]";
	}
	

    @OneToMany(mappedBy = "usuario")
	public Set<UsuarioRolePermission> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioRolePermission> usuarios) {
		this.usuarios = usuarios;
	}

	public static String getFieldNameUsingMap(String props) {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("nomeUsuario", "NOME_USUARIO");
        fieldMap.put("codUsuario", "COD_USUARIO");
        fieldMap.put("email", "email");

        return fieldMap.getOrDefault(props, "ID_USUARIO");  
    }
		
}
