package com.academico.models.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Disciplina;
import com.academico.models.repository.projection.DisciplinaProfessorProjection;
import com.academico.models.service.dto.request.DisciplinaRequest;
import com.academico.models.service.dto.response.DisciplinaResponse;

public class DisciplinaConverter {
	
	public static Disciplina toDisciplina(DisciplinaRequest disciplinaRequest) {
		return new Disciplina(
				disciplinaRequest.getIdDisciplina(),
				disciplinaRequest.getCodDisciplina(),
				disciplinaRequest.getNomeDisciplina()
				);
	}
	
	
	public static DisciplinaResponse toDisciplinaResponse(Disciplina disciplina) {
		return new DisciplinaResponse(
				disciplina.getIdDisciplina(), 
				disciplina.getCodDisciplina(),
				disciplina.getNomeDisciplina(),
				disciplina.getProfessor().getIdProfessor(),
				disciplina.getProfessor().getNomeProfessor()
				);
	}


	public static List<DisciplinaResponse> projectionToListaDisciplinaResponse(List<DisciplinaProfessorProjection> projections) {
		return projections.stream()
	            .map(projection -> new DisciplinaResponse(
	                projection.getIdDisciplina(),
	                projection.getCodDisciplina(),
	                projection.getNomeDisciplina(),
	                projection.getNomeProfessor()
	            ))
	            .collect(Collectors.toList());
	}

}
