package com.academico.models.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;


import com.academico.models.model.Disciplina;
import com.academico.models.model.Professor;
import com.academico.models.repository.DisciplinaRepository;
import com.academico.models.repository.ProfessorRepository;
import com.academico.models.service.DisciplinaService;
import com.academico.models.service.dto.converter.DisciplinaConverter;
import com.academico.models.service.dto.request.DisciplinaRequest;
import com.academico.models.service.dto.response.DisciplinaResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;
import com.academico.models.service.pagination.Pageable;

@Service
@Transactional
public class DisciplinaServiceImpl implements DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;


	
	@Autowired
	private ProfessorRepository professorRepository;
	

	
	@Override
	public DisciplinaResponse save(DisciplinaRequest entity) {

		var disciplina = DisciplinaConverter.toDisciplina(entity);

		Professor professor = professorRepository.findById(entity.getIdProfessor())
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Professor não localizado com id = %s", String.valueOf(entity.getIdProfessor()))));

		disciplina.setProfessor(professor);
		try {

			disciplina = disciplinaRepository.saveAndFlush(disciplina);
			return DisciplinaConverter.toDisciplinaResponse(disciplina);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(" Erro ao tentar incluir a disciplina "));
		}

		
	}
	

	@Override
	public DisciplinaResponse update(Long id, DisciplinaRequest entity) {

		ValidarDados.validarId(id,"disciplina");
	
		var disciplina = DisciplinaConverter.toDisciplina(entity);

		try {


			Disciplina disciplinaCadastrado = disciplinaRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("Disciplina não localizada %s", String.valueOf(id))));

			atualizarDadosDisciplina(disciplinaCadastrado, disciplina);
			
			disciplinaCadastrado = disciplinaRepository.saveAndFlush(disciplinaCadastrado);
			return DisciplinaConverter.toDisciplinaResponse(disciplinaCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar a disciplina com id %s", String.valueOf(id)));
		}

	}
	
	
	@Override
	public DisciplinaResponse updateField(Long id, Map<String, Object> fields) {
		
		ValidarDados.validarId(id,"disciplina");
		
		try {
			
			Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não localizada..."));
		
			fields.forEach((key, value)->{
				Field field = ReflectionUtils.findField(Disciplina.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, disciplina, value);
			});
		
			Disciplina disciplinaRegistrado  = disciplinaRepository.saveAndFlush(disciplina);
			
			return DisciplinaConverter.toDisciplinaResponse(disciplinaRegistrado);
			
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", String.valueOf(id)));
		}

		
	}

	
	@Override
	public void deleteById(Long id) {
		
		ValidarDados.validarId(id,"disciplina");
		
		try {
			disciplinaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage("Registro solicitado para exclusão não foi encontrado com id = %s",
					String.valueOf(id)));
		} catch (Exception e) {
			throw new RuntimeException(
					FormatMessage.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", String.valueOf(id)));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public DisciplinaResponse findById(Long id) {
		
		ValidarDados.validarId(id,"disciplina");
		
		var disciplina = disciplinaRepository.findByidDisciplina(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Disciplina não localizada %s", String.valueOf(id))));

		var disciplinaResponse = DisciplinaConverter.toDisciplinaResponse(disciplina);

		return disciplinaResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DisciplinaResponse> listar() {

		var listaDisciplina = disciplinaRepository.listagemDisciplina();

		List<DisciplinaResponse> listaDisciplinaResponse = DisciplinaConverter.projectionToListaDisciplinaResponse(listaDisciplina);

		return listaDisciplinaResponse;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<DisciplinaResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {
		
		String var = Disciplina.getFieldNameUsingMap(props);

        int totalRegistros = countDisciplinas(key);

        Pageable pageable = Pageable.of(page, pageSize, dir, var);
		        
		List<DisciplinaResponse> pagina = this.findDisciplinas(key, pageable);
		
		Page<DisciplinaResponse> pagination = Page.createPagination(
				                                             pagina, 
				                                             totalRegistros, 
				                                             pageable.getPageSize(), 
				                                             pageable.getPage() );

		return pagination;
	}

	private int countDisciplinas(String key ) {
	       return disciplinaRepository.totalRegistrosDisciplina(key).intValue();
	}

	private List<DisciplinaResponse> findDisciplinas(String key, Pageable pageable) {

		
		List<Object[]> lista = disciplinaRepository.buscarRegistrosPaginados(key, pageable);	
		
		List<DisciplinaResponse> disciplinaResponses = new ArrayList<>();
		

		for (Object[] row : lista) {
			
			BigDecimal idDisciplina = (BigDecimal) row[0];
		    String codDisciplina = (String) row[1];
		    String nomeDisciplina = (String) row[2];
		    BigDecimal idProfessor = (BigDecimal) row[3];
		    String nome_professor = (String) row[4];
		    BigDecimal rowNum = (BigDecimal) row[5]; 
		  

		    DisciplinaResponse disciplinaResponse = new DisciplinaResponse(
	             idDisciplina, 
	             codDisciplina, 
	             nomeDisciplina, 
	             idProfessor, 
	             nome_professor, 
	             rowNum
		    );
		    
		    disciplinaResponses.add(disciplinaResponse);
		}
		
		
		
		
		return disciplinaResponses;
	}
	
	
		
	
	
	

	
	
	private void atualizarDadosDisciplina(Disciplina disciplinaCadastrado, Disciplina disciplina) {
	
	    disciplinaCadastrado.setCodDisciplina(disciplina.getCodDisciplina());
	    disciplinaCadastrado.setNomeDisciplina(disciplina.getNomeDisciplina());
		
     
      	ValidarDados.validarId(disciplina.getProfessor().getIdProfessor(), "professor");
	    	
	   	professorRepository.findById(disciplina.getProfessor().getIdProfessor())
			.orElseThrow(() -> new EntityNotFoundException(
					String.format("Professor não localizada com id = %s", String.valueOf(disciplina.getProfessor().getIdProfessor()))));

	    
	} 



}
