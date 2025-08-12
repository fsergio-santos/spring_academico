package com.academico.models.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Professor;
import com.academico.models.model.Usuario;
import com.academico.models.repository.ProfessorRepository;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.ProfessorService;
import com.academico.models.service.dto.converter.ProfessorConverter;
import com.academico.models.service.dto.request.ProfessorRequest;
import com.academico.models.service.dto.response.ProfessorResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;
import com.academico.models.service.pagination.PageRequestConfig;


@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public ProfessorResponse save(ProfessorRequest entity) {

		var professor = ProfessorConverter.toProfessor(entity);
		
		
		Usuario usuario = usuarioRepository.findById(entity.getIdUsuario()).orElseThrow(() -> new EntityNotFoundException(
				FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(entity.getIdUsuario()))));

		professor.setUsuario(usuario);

		try {

			professor = professorRepository.saveAndFlush(professor);
			return ProfessorConverter.toProfessorResponse(professor);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(" Erro ao tentar incluir o professor "));
		}

		
	}
	

	@Override
	public ProfessorResponse update(Long id, ProfessorRequest entity) {

		ValidarDados.validarId(id, "professor");


		Professor professor = ProfessorConverter.toProfessor(entity);
		
		Usuario usuario = usuarioRepository.findById(entity.getIdUsuario()).orElseThrow(() -> new EntityNotFoundException(
				FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(entity.getIdUsuario()))));

        professor.setUsuario(usuario);
		
		try {
			Professor professorCadastrado = professorRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("Professor não localizada %s", id)));
		
			professorCadastrado.setCodProfessor(professor.getCodProfessor());
			professorCadastrado.setNomeProfessor(professor.getNomeProfessor());
			professorCadastrado.setUsuario(professor.getUsuario());
			
			professorCadastrado = professorRepository.saveAndFlush(professorCadastrado);
			return ProfessorConverter.toProfessorResponse(professorCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", id));
		}

	}
	
	@Override
	public ProfessorResponse updateField(Long id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		
		ValidarDados.validarId(id, "professor");

		try {
			professorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage("Registro solicitado para exclusão não foi encontrado com id = %s",id));
		} catch (Exception e) {
			throw new RuntimeException(
					FormatMessage.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ProfessorResponse findById(Long id) {
		
		ValidarDados.validarId(id, "professor");
		
		var professor = professorRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Professor não localizada %s", id)));

		var professorResponse = ProfessorConverter.toProfessorResponse(professor);

		return professorResponse;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<ProfessorResponse> listar() {

		var listaProfessor = professorRepository.findAll();

		List<ProfessorResponse> lista = ProfessorConverter.toListaProfessorResponse(listaProfessor);

		return lista;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProfessorResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {
		
		Pageable pageable = PageRequestConfig.gerarPagina(page, pageSize);

		String var = Professor.getFieldNameUsingMap(props);

		List<Professor> listaProfessors = findProfessors(key, pageable, var, dir);

		int total = countProfessor(key);

		Page<ProfessorResponse> pagina = Page.createPagination(
				ProfessorConverter.toListaProfessorResponse(listaProfessors), 
				total,pageable.getPageSize(), pageable.getPageNumber() );

		return pagina;
	}

	
	private List<Professor> findProfessors(String key, Pageable pageable, String sortField, String dir) {
		if (Objects.isNull(key)) {
			return professorRepository.findProfessorPaginados((int) pageable.getOffset(), pageable.getPageSize());
		}
//		return professorRepository.buscarRegistrosPaginados(key, (int) pageable.getOffset(), pageable.getPageSize(),
//				sortField, dir);
		return null;
	}

	private int countProfessor(String key) {
		if (Objects.isNull(key)) {
			return professorRepository.countProfessors();
		}
		return professorRepository.countProfessorsByKey(key);
	}

	
}
