package com.academico.models.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Professor;
import com.academico.models.service.dto.request.ProfessorRequest;
import com.academico.models.service.dto.response.ProfessorResponse;

public class ProfessorConverter {
	
	
	public static Professor toProfessor(ProfessorRequest professorRequest) {
		return new Professor(
				professorRequest.getIdProfessor(),
				professorRequest.getCodProfessor(),
				professorRequest.getNomeProfessor()
		);
	};
	
	
	
	public static ProfessorResponse toProfessorResponse(Professor professor) {
		return new ProfessorResponse(
				professor.getIdProfessor(),
				professor.getCodProfessor(),
				professor.getNomeProfessor(),
				professor.getUsuario().getIdUsuario(),
				professor.getUsuario().getNomeUsuario()
		);
	}
	
	
	
	public static List<ProfessorResponse> toListaProfessorResponse(List<Professor> listaProfessors){
		return listaProfessors.stream()
                .map(ProfessorConverter::toProfessorResponse) 
                .collect(Collectors.toList()); 
	}
	

}
