package com.academico.models.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.academico.config.SpringProjectConfig;
import com.academico.models.model.Aluno;
import com.academico.models.model.Cidade;
import com.academico.models.model.Professor;
import com.academico.models.model.Usuario;
import com.academico.models.repository.AlunoRepository;
import com.academico.models.repository.CidadeRepository;
import com.academico.models.repository.ProfessorRepository;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.UsuarioService;
import com.academico.models.service.dto.converter.UsuarioConverter;
import com.academico.models.service.dto.request.AlterarSenhaRequest;
import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.models.service.dto.response.ListaPagination;
import com.academico.models.service.dto.response.UsuarioResponse;
import com.academico.models.service.exception.EmailJaCadastradoException;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.exception.UntypedFieldsException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;
import com.academico.models.service.pagination.Pageable;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	// @Autowired
	// private CriptografarSenha crypt;

	@Override
	public UsuarioResponse save(UsuarioRequest entity) {

		var usuario = UsuarioConverter.toUsuario(entity);

		try {

			validarTipoUsuario(usuario);

			validarEmailUnico(usuario);

			Cidade cidade = cidadeRepository.findById(entity.getIdCidade())
					.orElseThrow(() -> new EntityNotFoundException(
							String.format("Cidade não localizada com id = %s", String.valueOf(entity.getIdCidade()))));

			usuario.setCidade(cidade);

			usuario = usuarioRepository.saveAndFlush(usuario);

			if (usuario.getTipo() == 1) {

				validarDadosProfessor(entity);
				Professor professor = criarProfessor(entity);
				professor.setUsuario(usuario);
				professorRepository.save(professor);

			} else {

				validarDadosAluno(entity);
				Aluno aluno = criarAluno(entity);
				aluno.setUsuario(usuario);
				aluno = alunoRepository.save(aluno);

			}
			return UsuarioConverter.toUsuarioResponse(usuario);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(e.getMessage()));
		}

	}

	@Override
	public UsuarioResponse update(Long id, UsuarioRequest entity) {

		ValidarDados.validarId(id, "usuário");

		var usuario = UsuarioConverter.toUsuario(entity);

		validarTipoUsuario(usuario);
		
		validarEmailUnico(usuario);

		try {
			
			Usuario usuarioCadastrado = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
					FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(id))));

			atualizarDadosUsuario(usuarioCadastrado, usuario);

			Cidade cidade = cidadeRepository.findById(entity.getIdCidade())
					.orElseThrow(() -> new EntityNotFoundException(
							String.format("Cidade não localizada com id = %s", String.valueOf(entity.getIdCidade()))));

			usuario.setCidade(cidade);

			if (usuario.getTipo() == 1) {

				validarDadosProfessor(entity);
				Professor professor = criarProfessor(entity);
				professor.setUsuario(usuario);
				professorRepository.save(professor);
				usuario.setProfessor(professor);

			} else {

				validarDadosAluno(entity);
				Aluno aluno = criarAluno(entity);
				aluno.setUsuario(usuario);
				aluno = alunoRepository.save(aluno);
				usuario.setAluno(aluno);

			}

			usuarioCadastrado = usuarioRepository.save(usuarioCadastrado);

			return UsuarioConverter.toUsuarioResponse(usuarioCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", String.valueOf(id)));
		}

	}

	@Override
	public UsuarioResponse updateField(Long id, Map<String, Object> fields) {

		try {

			Usuario usuario = usuarioRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Entidade não localizada..."));

			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Usuario.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, usuario, value);
			});

			Usuario usuarioRegistrado = usuarioRepository.saveAndFlush(usuario);

			return UsuarioConverter.toUsuarioResponse(usuarioRegistrado);

		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", String.valueOf(id)));
		}

	}

	@Override
	public void deleteById(Long id) {

		ValidarDados.validarId(id, "usuário");

		try {
			usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage(
					"Registro solicitado para exclusão não foi encontrado com id = %s", String.valueOf(id)));
		} catch (Exception e) {
			throw new RuntimeException(FormatMessage
					.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", String.valueOf(id)));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findById(Long id) {

		ValidarDados.validarId(id, "usuário");

		var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(id))));

		var usuarioResponse = UsuarioConverter.toUsuarioResponse(usuario);

		return usuarioResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioResponse> listar() {

		var listaUsuario = usuarioRepository.listagemUsuario();

		List<UsuarioResponse> listaUsuarioResponse = UsuarioConverter.projectionToListaUsuarioResponse(listaUsuario);

		return listaUsuarioResponse;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {

		String var = Usuario.getFieldNameUsingMap(props);

		Pageable pageable = Pageable.of(page, pageSize, dir, var);

		return gerarPagina(key, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> getUsuarioPagination(String key, Integer page, Integer pageSize, String dir,
			String props) {

		String var = Usuario.getFieldNameUsingMap(props);

		Pageable pageable = Pageable.of(page, pageSize, dir, var);

		return gerarPagina(key, pageable);
	}

	@Override
	public void alterarSenha(AlterarSenhaRequest alterarSenhaRequest) {

		ValidarDados.validarId(alterarSenhaRequest.getIdUsuario(), "usuário");

		Usuario usuario = usuarioRepository.findUsuarioById(alterarSenhaRequest.getIdUsuario())
				.orElseThrow(() -> new EntityNotFoundException(FormatMessage.formatMessage("Usuario não localizada %s",
						String.valueOf(alterarSenhaRequest.getIdUsuario()))));

		ValidarDados.confirmSenhaIsValid(alterarSenhaRequest.getSenha(), alterarSenhaRequest.getConfirmSenha());

		usuario.setSenha(alterarSenhaRequest.getSenha());

		try {

			usuarioRepository.updatePassword(usuario.getIdUsuario(), usuario.getSenha());

		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar a senha do usuário com id %s",
							String.valueOf(alterarSenhaRequest.getIdUsuario())));
		}

	}

	@Override
	public Map<String, Long> calculateTotalUsuario() {

		List<Object[]> resultados = usuarioRepository.totalsByTipoUsuario();

		Map<String, Long> totais = new HashMap<String, Long>();
		for (Object[] resultado : resultados) {
			Integer tipoUsuario = (Integer) resultado[0];
			String tipoUsuarioDescricao = tipoUsuario == 1 ? "Professor" : "Aluno";
			Long total = (Long) resultado[1];
			totais.put(tipoUsuarioDescricao, total);
		}

		return totais;
	}

	@Override
	public UsuarioResponse findUsuarioById(Long id) {
		ValidarDados.validarId(id, "usuário");

		var usuario = usuarioRepository.findByidUsuario(id).orElseThrow(() -> new EntityNotFoundException(
				FormatMessage.formatMessage("Usuario não localizada %s", String.valueOf(id))));

		var usuarioResponse = UsuarioConverter.toUsuarioResponse(usuario);

		return usuarioResponse;
	}

	private Page<UsuarioResponse> gerarPagina(String key, Pageable pageable) {
		ListaPagination pagina = usuarioRepository.buscarRegistrosPaginados(key, pageable);

		List<UsuarioResponse> usuarioResponses = new ArrayList<>();

		for (Object[] row : pagina.getLista()) {

			BigDecimal id = (BigDecimal) row[0];
			String codUsuario = (String) row[1];
			String nomeUsuario = (String) row[2];
			String email = (String) row[3];
			BigDecimal tipo = (BigDecimal) row[4];
			BigDecimal idCidade = (BigDecimal) row[5];
			String foto = (String) row[6];
			String senha = (String) row[7];
			Boolean ativo = (Boolean) row[8];
			String nome_cidade = (String) row[9];
			BigDecimal rowNum = (BigDecimal) row[10];

			UsuarioResponse usuarioResponse = new UsuarioResponse(id, codUsuario, nomeUsuario, email, senha, ativo,
					foto, tipo, idCidade, rowNum, nome_cidade);

			usuarioResponses.add(usuarioResponse);
		}

		Page<UsuarioResponse> pagination = Page.createPagination(usuarioResponses, pagina.getTotalCount(),
				pageable.getPageSize(), pageable.getPage());

		return pagination;
	}

	private void validarEmailUnico(Usuario usuario) {

		Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findUsuarioByEmail(usuario.getEmail());

		if (usuarioExistenteOpt.isPresent()) {
			Usuario usuarioExistente = usuarioExistenteOpt.get();

			// Se for um novo usuário (id nulo) e o email já existir, lançar exceção
			if (usuario.getIdUsuario() == null) {
				throw new EmailJaCadastradoException(
						FormatMessage.formatMessage("E-mail já cadastrado: ", usuario.getEmail()));
			}

			// Se for uma atualização e o email pertence a outro usuário, lançar exceção
			if (!usuarioExistente.getIdUsuario().equals(usuario.getIdUsuario())) {
				throw new EmailJaCadastradoException(
						FormatMessage.formatMessage("E-mail já cadastrado para outro usuário: ", usuario.getEmail()));
			}
		}
	}

	private void validarTipoUsuario(Usuario usuario) {
		if (!SpringProjectConfig.VALORES_VALIDOS.contains(usuario.getTipo())) {
			throw new IllegalArgumentException(
					FormatMessage.formatMessage("O tipo do usuário = s% não é válido  ", usuario.getTipo()));
		}
	}

	private void atualizarDadosUsuario(Usuario usuarioCadastrado, Usuario usuario) {

		usuarioCadastrado.setCodUsuario(usuario.getCodUsuario());
		usuarioCadastrado.setNomeUsuario(usuario.getNomeUsuario());
		usuarioCadastrado.setEmail(usuario.getEmail());

		if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
			usuarioCadastrado.setSenha(usuario.getSenha());
		}

		usuarioCadastrado.setFoto(usuario.getFoto());
		usuarioCadastrado.setTipo(usuario.getTipo());
		usuarioCadastrado.setCidade(usuario.getCidade());
		usuarioCadastrado.setFoto(usuario.getFoto());
		usuarioCadastrado.setAtivo(usuario.isAtivo());

		if (usuario.getTipo() == 1) {

			ValidarDados.validarId(usuario.getProfessor().getIdProfessor(), "professor");

			professorRepository.findById(usuario.getProfessor().getIdProfessor())
					.orElseThrow(() -> new EntityNotFoundException(String.format("Professor não localizada com id = %s",
							String.valueOf(usuario.getProfessor().getIdProfessor()))));

			// validarDadosProfessor(usuario);
			usuarioCadastrado.getProfessor().setCodProfessor(usuario.getProfessor().getCodProfessor());
			usuarioCadastrado.getProfessor().setNomeProfessor(usuario.getProfessor().getNomeProfessor());
		} else if (usuario.getTipo() == 2) {

			ValidarDados.validarId(usuario.getProfessor().getIdProfessor(), "aluno");
			alunoRepository.findById(usuario.getAluno().getIdAluno())
					.orElseThrow(() -> new EntityNotFoundException(String.format("Aluno não localizada com id = %s",
							String.valueOf(usuario.getAluno().getIdAluno()))));

			// validarDadosAluno(usuario);
			usuarioCadastrado.getAluno().setCodAluno(usuario.getAluno().getCodAluno());
			usuarioCadastrado.getAluno().setNomeAluno(usuario.getAluno().getNomeAluno());
			usuarioCadastrado.getAluno().setIdade(usuario.getAluno().getIdade());
		}
	}

	protected void validarDadosProfessor(UsuarioRequest usuario) {
		if (Objects.isNull(usuario.getNomeProfessor()) || usuario.getNomeProfessor().isBlank()
				|| usuario.getNomeProfessor().isEmpty()) {
			throw new UntypedFieldsException(
					FormatMessage.formatMessage("Nome do professor não foi informado %s", usuario.getNomeProfessor()));
		}

		if (Objects.isNull(usuario.getCodProfessor()) || usuario.getCodProfessor().isBlank()
				|| usuario.getCodProfessor().isEmpty()) {
			throw new UntypedFieldsException(
					FormatMessage.formatMessage("Código do professor não foi informado %s", usuario.getCodProfessor()));
		}
	}

	protected void validarDadosAluno(UsuarioRequest usuario) {
		if (Objects.isNull(usuario.getNomeAluno()) || usuario.getNomeAluno().isBlank()
				|| usuario.getNomeAluno().isEmpty()) {
			throw new UntypedFieldsException(
					FormatMessage.formatMessage("Nome do aluno não foi informado %s", usuario.getNomeAluno()));
		}

		if (Objects.isNull(usuario.getCodAluno()) || usuario.getCodAluno().isBlank()
				|| usuario.getCodAluno().isEmpty()) {
			throw new UntypedFieldsException(
					FormatMessage.formatMessage("Código do aluno não foi informado %s", usuario.getCodAluno()));
		}

		if (Objects.isNull(usuario.getIdade()) || usuario.getIdade().toString().isBlank()
				|| usuario.getIdade().toString().isEmpty()) {
			throw new UntypedFieldsException(
					FormatMessage.formatMessage("A idade do aluno não foi informado %s", usuario.getIdade()));
		}
	}

	private Professor criarProfessor(UsuarioRequest usuario) {
		Professor professor = new Professor();

		if (usuario.getIdProfessor() != null) {
			professor = professorRepository.findById(usuario.getIdProfessor())
					.orElseThrow(() -> new EntityNotFoundException(
							FormatMessage.formatMessage("Professor não localizada %s", usuario.getIdProfessor())));
		}
		professor.setNomeProfessor(usuario.getNomeProfessor());
		professor.setCodProfessor(usuario.getCodProfessor());
		return professor;
	}

	private Aluno criarAluno(UsuarioRequest usuario) {
		Aluno aluno = new Aluno();

		if (usuario.getIdAluno() != null) {
			aluno = alunoRepository.findById(usuario.getIdAluno()).orElseThrow(() -> new EntityNotFoundException(
					FormatMessage.formatMessage("Aluno não localizada %s", usuario.getIdAluno())));
		}

		aluno.setCodAluno(usuario.getCodUsuario());
		aluno.setNomeAluno(usuario.getNomeUsuario());
		aluno.setIdade(usuario.getIdade());
		return aluno;
	}

}
