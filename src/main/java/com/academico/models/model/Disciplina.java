package com.academico.models.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "DISCIPLINA")
public class Disciplina {

	private Long idDisciplina;
	private String codDisciplina;
	private String nomeDisciplina;
	private Professor professor;
	
		
	public Disciplina() {
		super();
	}

	public Disciplina(Long idDisciplina, String codDisciplina, String nomeDisciplina) {
		super();
		this.idDisciplina = idDisciplina;
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISCIPLINA_SEQ")
	@SequenceGenerator(sequenceName = "sequencia_disciplina",initialValue = 1, allocationSize = 1, name = "DISCIPLINA_SEQ")
	@Column(name = "ID_DISCIPLINA")
	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	@Column( name = "COD_DISCIPLINA" )
	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	@Column( name = "NOME_DISCIPLINA" )
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROFESSOR")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDisciplina);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equals(idDisciplina, other.idDisciplina);
	}

	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", codDisciplina=" + codDisciplina + ", nomeDisciplina="
				+ nomeDisciplina + ", professor=" + professor + "]";
	}
	
	
	public static String getFieldNameUsingMap(String props) {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("nomeDisciplina", "nome_disciplina");
        fieldMap.put("codDisciplina", "cod_disciplina");
     
        return fieldMap.getOrDefault(props, "id_disciplina");  
    }
	
}
