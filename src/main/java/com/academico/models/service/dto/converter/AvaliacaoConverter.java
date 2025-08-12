package com.academico.models.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Avaliacao;
import com.academico.models.service.dto.request.AvaliacaoRequest;
import com.academico.models.service.dto.response.AvaliacaoResponse;

public class AvaliacaoConverter {

		
	public static Avaliacao toAvaliacao(AvaliacaoRequest avaliacaoRequest) {
		return new Avaliacao(
				avaliacaoRequest.getIdAvaliacao(),
				avaliacaoRequest.getCodAvaliacao(),
				avaliacaoRequest.getDescricaoAvaliacao()
		);
	}
	
	
	public static AvaliacaoResponse toAvaliacaoResponse(Avaliacao avaliacao) {
		return new AvaliacaoResponse(
				avaliacao.getIdAvaliacao(), 
				avaliacao.getCodAvaliacao(), 
				avaliacao.getDescricaoAvaliacao(), 
				avaliacao.getDisciplina().getIdDisciplina(),
				avaliacao.getDisciplina().getNomeDisciplina(), 
				avaliacao.getDisciplina().getProfessor().getIdProfessor(), 
				avaliacao.getDisciplina().getProfessor().getNomeProfessor()
		);
	}
	
	public static List<AvaliacaoResponse> toAvaliacaoResponse(List<Avaliacao> listaAvaliacao){
		return listaAvaliacao.stream()
                .map(AvaliacaoConverter::toAvaliacaoResponse) 
                .collect(Collectors.toList()); 
	}
	
}
