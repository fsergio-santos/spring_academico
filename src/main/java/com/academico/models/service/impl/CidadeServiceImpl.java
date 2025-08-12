package com.academico.models.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Cidade;
import com.academico.models.repository.CidadeRepository;
import com.academico.models.service.CidadeService;
import com.academico.models.service.dto.converter.CidadeConverter;
import com.academico.models.service.dto.request.CidadeRequest;
import com.academico.models.service.dto.response.CidadeResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;

@Service
@Transactional
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;



	@Override
	public CidadeResponse save(CidadeRequest entity) {

		var cidade = CidadeConverter.toCidade(entity);

		try {

			cidade = cidadeRepository.saveAndFlush(cidade);
			return CidadeConverter.toCidadeResponse(cidade);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(" Erro ao tentar incluir o cidade "));
		}

		
	}
	

	@Override
	public CidadeResponse update(Long id, CidadeRequest entity) {

		ValidarDados.validarId(id,"cidade");

		Cidade cidade = CidadeConverter.toCidade(entity);

		try {
			Cidade cidadeCadastrado = cidadeRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("Cidade não localizada %s", id)));
		
			cidadeCadastrado.setCodCidade(cidade.getCodCidade());
			cidadeCadastrado.setNomeCidade(cidade.getNomeCidade());
			
			cidadeCadastrado = cidadeRepository.saveAndFlush(cidadeCadastrado);
			return CidadeConverter.toCidadeResponse(cidadeCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", id));
		}

	}
	
	@Override
	public CidadeResponse updateField(Long id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		
		ValidarDados.validarId(id,"cidade");
	
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage("Registro solicitado para exclusão não foi encontrado com id = %s",id));
		} catch (Exception e) {
			throw new RuntimeException(
					FormatMessage.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public CidadeResponse findById(Long id) {
		
		ValidarDados.validarId(id,"usuário");
		
		
		var cidade = cidadeRepository.findByidCidade(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Cidade não localizada %s", id)));

		var cidadeResponse = CidadeConverter.toCidadeResponse(cidade);

		return cidadeResponse;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<CidadeResponse> listar() {

		var listaCidade = cidadeRepository.listaCidades();
		List<CidadeResponse> lista = CidadeConverter.toCidadeResponse(listaCidade);

		return lista;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<CidadeResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {
	    return null;

	}

	
}