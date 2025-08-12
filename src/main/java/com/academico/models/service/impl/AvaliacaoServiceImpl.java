package com.academico.models.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.repository.AvaliacaoRepository;
import com.academico.models.service.AvaliacaoService;
import com.academico.models.service.dto.converter.AvaliacaoConverter;
import com.academico.models.service.dto.request.AvaliacaoRequest;
import com.academico.models.service.dto.response.AvaliacaoResponse;


@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	
	@Override
	public List<AvaliacaoResponse> listar() {
		var listaAvaliacao = avaliacaoRepository.findAll();

		List<AvaliacaoResponse> lista = AvaliacaoConverter.toAvaliacaoResponse(listaAvaliacao);

		return lista;
	}

	@Override
	public AvaliacaoResponse save(AvaliacaoRequest entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AvaliacaoResponse update(Long id, AvaliacaoRequest entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AvaliacaoResponse updateField(Long id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public AvaliacaoResponse findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
