package com.academico.models.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.config.SpringProjectConfig;
import com.academico.models.model.Usuario;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.JwtService;
import com.academico.models.service.RegistrarUsuarioService;
import com.academico.models.service.components.CriptografarSenha;
import com.academico.models.service.components.SendEmail;
import com.academico.models.service.dto.converter.UsuarioConverter;
import com.academico.models.service.dto.mail.Mail;
import com.academico.models.service.dto.request.RegistrarRequest;
import com.academico.models.service.exception.AccessForbiddenException;
import com.academico.models.service.exception.EmailJaCadastradoException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.exception.UserNotFoundException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;

@Service
@Transactional
public class RegistrarUsuarioServiceImpl implements RegistrarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CriptografarSenha crypt;
	
	@Autowired
	private SendEmail sendEmail;
	
    @Autowired
	private JwtService jwtService;

	@Override
	public void save(RegistrarRequest registrarRequest) {
        
		var usuario = UsuarioConverter.toUsuario(registrarRequest);

		ValidarDados.confirmSenhaIsValid(usuario.getSenha(), registrarRequest.getConfirmSenha());

		validarEmailUnico(usuario);
		
		usuario.setSenha(crypt.encryptPassword(usuario.getSenha()));
		
		usuario.setAtivo(false);

		try {

			usuario = usuarioRepository.saveAndFlush(usuario);
			String access_token = jwtService.registerTokenAccess(usuario);
			
			Mail mail = new Mail();
			
			mail.setTo(usuario.getEmail());
			mail.setFrom(SpringProjectConfig.EMAIL_ENVIO);
			mail.setSubject("Registro realizado no sistema acadêmico");
			mail.setTemplate("email-template");
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("Nome", usuario.getNomeUsuario());
			model.put("Sistema","http://localhost:3000");
			model.put("url","http://localhost:8080/rest/registrar/ativar?token="+access_token);
		    mail.setModel(model);
		    
		    sendEmail.createEmail(mail);
		    
		} catch (DataIntegrityViolationException e) {
            throw new NegocioException(FormatMessage.formatMessage("Violação de integridade na tabela de usuários"));
        }  catch (DataAccessException e) {
            throw new NegocioException(FormatMessage.formatMessage("Erro no acesso ao banco de dados."));
        }

	}
	
	
	@Override
	public void activate(String token) {
		
	
		String email = jwtService.getEmail(token);
		
		Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(
				() -> new UserNotFoundException(FormatMessage.formatMessage("Usuário não cadastrado")));
		

	    if (!jwtService.verifyToken(token)) {
			throw new AccessForbiddenException(FormatMessage.formatMessage("Token inválido"));
		}
		
	    
	    Mail mail = new Mail();
		
		mail.setTo(usuario.getEmail());
		mail.setFrom("no-reply@academico.edu.br");
		mail.setSubject("Usuário ativado no sistema ");
		mail.setTemplate("login-template");
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("Nome", usuario.getNomeUsuario());
		model.put("Sistema","http://localhost:3000");
		model.put("url","http://localhost:3000/login");
	    
		mail.setModel(model);
		
		sendEmail.createEmail(mail);
		
		try {
			usuarioRepository.activate(usuario.getIdUsuario(), true);
		} catch (DataIntegrityViolationException e) {
            throw new NegocioException(FormatMessage.formatMessage("Violação de integridade na tabela de usuários"));
        }  catch (DataAccessException e) {
            throw new NegocioException(FormatMessage.formatMessage("Erro de acesso ao banco de dados."));
        }
	}
	
	private void validarEmailUnico(Usuario usuario) {

		Optional<Usuario> usuarioExistente = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent()) {
				
			if (usuario.getIdUsuario() == null) {
				throw new EmailJaCadastradoException(
						FormatMessage.formatMessage("E-mail já cadastrado: ", usuario.getEmail()));
			} else {
				if (!usuarioExistente.get().getIdUsuario().equals(usuario.getIdUsuario())) {
					throw new EmailJaCadastradoException(
							FormatMessage.formatMessage("E-mail já cadastrado para outro usuário: " + usuario.getEmail()));
			    }
			}
		}
	}

	
}
