package com.academico.models.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Aluno;
import com.academico.models.service.dto.request.AlunoRequest;
import com.academico.models.service.dto.response.AlunoResponse;

public class AlunoConverter {
	
	
	public static Aluno toAluno(AlunoRequest alunoRequest) {
		return new Aluno(
				alunoRequest.getIdAluno(),
				alunoRequest.getCodAluno(),
				alunoRequest.getNomeAluno(),
				alunoRequest.getIdade()
				
		);
	};
	
	
	
	public static AlunoResponse toAlunoResponse(Aluno aluno) {
		return new AlunoResponse(
				aluno.getIdAluno(),
				aluno.getCodAluno(),
				aluno.getNomeAluno(),
				aluno.getIdade(),
				aluno.getUsuario().getIdUsuario(),
				aluno.getUsuario().getNomeUsuario()
		);
	}
	
	
	
	public static List<AlunoResponse> toAlunoResponse(List<Aluno> listaAlunos){
		return listaAlunos.stream()
                .map(AlunoConverter::toAlunoResponse) 
                .collect(Collectors.toList()); 
	}
	

}
