package com.academico.models.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Cidade;
import com.academico.models.service.dto.request.CidadeRequest;
import com.academico.models.service.dto.response.CidadeResponse;

public class CidadeConverter {
	
	public static Cidade toCidade(CidadeRequest cidadeRequest) {
		return new Cidade(
				cidadeRequest.getIdCidade(),
				cidadeRequest.getCodCidade(),
				cidadeRequest.getNomeCidade()
				);
		
	}
	
	public static CidadeResponse toCidadeResponse(Cidade cidade) {
		
		return new CidadeResponse(
				    cidade.getIdCidade(),
				    cidade.getCodCidade(),
				    cidade.getNomeCidade()
				);
	}
	
	
	public static List<CidadeResponse> toCidadeResponse(List<Cidade> listaCidades){
		 return listaCidades.stream()
                 .map(CidadeConverter::toCidadeResponse) 
                 .collect(Collectors.toList());
		
	}
}
