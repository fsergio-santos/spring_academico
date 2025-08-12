package com.academico.models.service.impl;


import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Aluno;
import com.academico.models.model.Usuario;
import com.academico.models.repository.AlunoRepository;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.AlunoService;
import com.academico.models.service.dto.converter.AlunoConverter;
import com.academico.models.service.dto.request.AlunoRequest;
import com.academico.models.service.dto.response.AlunoResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;
import com.academico.models.service.pagination.PageRequestConfig;


@Service
@Transactional
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Override
	public AlunoResponse save(AlunoRequest entity) {

		var aluno = AlunoConverter.toAluno(entity);
		
		Usuario usuario = usuarioRepository.findById(entity.getIdUsuario()).orElseThrow(() -> new EntityNotFoundException(
				FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(entity.getIdUsuario()))));

		aluno.setUsuario(usuario);

		try {

			aluno = alunoRepository.saveAndFlush(aluno);
			return AlunoConverter.toAlunoResponse(aluno);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(" Erro ao tentar incluir o aluno "));
		}

		
	}
	

	@Override
	public AlunoResponse update(Long id, AlunoRequest entity) {

		ValidarDados.validarId(id, "aluno");

		Aluno aluno = AlunoConverter.toAluno(entity); 
		
		Usuario usuario = usuarioRepository.findById(entity.getIdUsuario()).orElseThrow(() -> new EntityNotFoundException(
				FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(entity.getIdUsuario()))));

		aluno.setUsuario(usuario);

		try {
			Aluno alunoCadastrado = alunoRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("Aluno não localizada %s", id)));
		
			alunoCadastrado.setCodAluno(aluno.getCodAluno());
			alunoCadastrado.setIdade(aluno.getIdade());
			alunoCadastrado.setNomeAluno(aluno.getNomeAluno());
			alunoCadastrado.setIdade(aluno.getIdade());
			alunoCadastrado.setUsuario(aluno.getUsuario());
			
			alunoCadastrado = alunoRepository.saveAndFlush(alunoCadastrado);
			return AlunoConverter.toAlunoResponse(alunoCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", id));
		}

	}

	@Override
	public AlunoResponse updateField(Long id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void deleteById(Long id) {
		
		ValidarDados.validarId(id, "aluno");
		
		try {
			alunoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage("Registro solicitado para exclusão não foi encontrado com id = %s",id));
		} catch (Exception e) {
			throw new RuntimeException(
					FormatMessage.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AlunoResponse findById(Long id) {
		
		ValidarDados.validarId(id, "aluno");
		
		
		var aluno = alunoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Aluno não localizada %s", id)));

		var alunoResponse = AlunoConverter.toAlunoResponse(aluno);

		return alunoResponse;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<AlunoResponse> listar() {

		var listaAluno = alunoRepository.findAll();

		List<AlunoResponse> lista = AlunoConverter.toAlunoResponse(listaAluno);

		return lista;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<AlunoResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {
		
		Pageable pageable = PageRequestConfig.gerarPagina(page, pageSize);

		String var = Aluno.getFieldNameUsingMap(props);

		List<Aluno> listaAlunos = findAlunos(key, pageable, var, dir);

		int total = countAluno(key);

		Page<AlunoResponse> pagination = Page.createPagination(
				AlunoConverter.toAlunoResponse(listaAlunos), 
                total, 
                pageable.getPageSize(), 
                pageable.getPageNumber() );
 
        return pagination;
	}

	

	private List<Aluno> findAlunos(String key, Pageable pageable, String sortField, String dir) {
		if (Objects.isNull(key)) {
			return alunoRepository.findAlunoPaginados((int) pageable.getOffset(), pageable.getPageSize());
		}
		return null;
//		return alunoRepository.listaPaginada(key, (int) pageable.getOffset(), pageable.getPageSize(),
//				sortField, dir);
	}

	private int countAluno(String key) {
		if (Objects.isNull(key)) {
			return alunoRepository.countAlunos();
		}
		return alunoRepository.countAlunosByKey(key);
	}

	
}
