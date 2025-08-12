package com.academico.models.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.academico.models.service.listener.AlunoServiceListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@EntityListeners(AlunoServiceListener.class)
@Entity
@Table(name = "ALUNO")
public class Aluno {
	
	private Long    idAluno;
	private String  codAluno;
	private String  nomeAluno; 
	private Integer idade;
	private Usuario usuario;
	
	public Aluno() {
	}
	
	public Aluno(Long idAluno, String codAluno, String nomeAluno) {
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
	}

	public Aluno(Long idAluno, String codAluno, String nomeAluno, Integer idade) {
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.idade = idade;
	}
    
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUNO_SEQ")
	@SequenceGenerator(sequenceName = "sequencia_aluno",initialValue = 1, allocationSize = 1, name = "ALUNO_SEQ")
	@Column(name = "ID_ALUNO")
	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	@Column(name = "COD_ALUNO")
	public String getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}

	@Column(name = "NOME_ALUNO")
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	@Column(name = "IDADE")
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}


	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAluno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(idAluno, other.idAluno);
	}

	@Override
	public String toString() {
		return "Aluno [idAluno=" + idAluno + ", codAluno=" + codAluno + ", nomeAluno=" + nomeAluno + ", idade=" + idade
				+ ", usuario=" + usuario + "]";
	}
	
	
	public static String getFieldNameUsingMap(String props) {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("nomeAluno", "nome_aluno");
        fieldMap.put("codAluno", "cod_aluno");
     
        return fieldMap.getOrDefault(props, "id_aluno");  
    }
	

}
