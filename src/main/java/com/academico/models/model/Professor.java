package com.academico.models.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROFESSOR")
@SequenceGenerator(sequenceName = "SEQUENCIA_PROFESSOR",initialValue = 1, allocationSize = 1, name = "PROFESSOR_SEQ")
public class Professor {
   
	private Long   idProfessor;
	private String codProfessor;
	private String nomeProfessor;
	
	private Usuario usuario;
	private List<Disciplina> disciplinas;

	public Professor() {
	}

	public Professor(Long idProfessor, String codProfessor, String nomeProfessor) {
		this.idProfessor = idProfessor;
		this.codProfessor = codProfessor;
		this.nomeProfessor = nomeProfessor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESSOR_SEQ")
	@Column(name = "ID_PROFESSOR")
	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}

	@Column(name = "COD_PROFESSOR")
	public String getCodProfessor() {
		return codProfessor;
	}

	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}

	@Column(name = "NOME_PROFESSOR")
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USUARIO",referencedColumnName = "ID_USUARIO")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	@OneToMany( mappedBy = "professor")
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProfessor);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(idProfessor, other.idProfessor);
	}

	@Override
	public String toString() {
		return "Professor [idProfessor=" + idProfessor + ", codProfessor=" + codProfessor + ", nomeProfessor="
				+ nomeProfessor + ", usuario=" + usuario + "]";
	}
	
	
	public static String getFieldNameUsingMap(String props) {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("nomeProfessor", "nome_professor");
        fieldMap.put("codProfessor", "cod_professor");
     
        return fieldMap.getOrDefault(props, "id_professor");  
    }
	
}
