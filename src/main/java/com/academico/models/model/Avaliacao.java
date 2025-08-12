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
@Table(name = "AVALIACAO")
public class Avaliacao {

	private Long idAvaliacao;
	private String codAvaliacao;
	private String descricaoAvaliacao;
	private Disciplina disciplina;

	public Avaliacao() {

	}

	public Avaliacao(Long idAvaliacao, String codAvaliacao, String descricaoAvaliacao) {
		this.idAvaliacao = idAvaliacao;
		this.codAvaliacao = codAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AVALIACAO_SEQ")
	@SequenceGenerator(sequenceName = "sequencia_avaliacao",initialValue = 1, allocationSize = 1, name = "AVALIACAO_SEQ")
	@Column(name = "ID_AVALIACAO")
	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	@Column( name = "COD_AVALIACAO" )
	public String getCodAvaliacao() {
		return codAvaliacao;
	}

	public void setCodAvaliacao(String codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}

	@Column( name = "DESCRICAO" )
	public String getDescricaoAvaliacao() {
		return descricaoAvaliacao;
	}

	public void setDescricaoAvaliacao(String descricaoAvaliacao) {
		this.descricaoAvaliacao = descricaoAvaliacao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DISCIPLINA")
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(idAvaliacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		return Objects.equals(idAvaliacao, other.idAvaliacao);
	}
	
	
	@Override
	public String toString() {
		return "Avaliacao [idAvaliacao=" + idAvaliacao + ", codAvaliacao=" + codAvaliacao + ", descricaoAvaliacao="
				+ descricaoAvaliacao + ", disciplina=" + disciplina + "]";
	}

	public static String getFieldNameUsingMap(String props) {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("descricaoAvaliacao", "descricao");
        fieldMap.put("codAvalicao", "cod_avaliacao");
     
        return fieldMap.getOrDefault(props, "id_avaliacao");  
    }

}
