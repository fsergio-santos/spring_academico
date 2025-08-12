package com.academico.models.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.config.SpringProjectConfig;
import com.academico.models.model.Usuario;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.JwtService;
import com.academico.models.service.UpdatePasswordService;
import com.academico.models.service.components.CriptografarSenha;
import com.academico.models.service.components.SendEmail;
import com.academico.models.service.dto.mail.Mail;
import com.academico.models.service.dto.request.UpdatePasswordRequest;
import com.academico.models.service.exception.AccessForbiddenException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.exception.UserNotFoundException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;

@Service
@Transactional
public class UpdatePasswordServiceImpl implements UpdatePasswordService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SendEmail sendEmail;
	
    @Autowired
	private JwtService jwtService;
    
	@Autowired
	private CriptografarSenha crypt;
	
	@Override
	public void updatePassword(String email) {
		
		Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(
				() -> new UserNotFoundException(FormatMessage.formatMessage("Usuário não cadastrado")));
		
		String access_token = jwtService.registerTokenAccess(usuario);
		
		try {
			String encodedToken = URLEncoder.encode(access_token, "UTF-8");
			Mail mail = new Mail();
			
			mail.setTo(usuario.getEmail());
			mail.setFrom(SpringProjectConfig.EMAIL_ENVIO);
			mail.setSubject("Solicitação de alteração da senha de acesso ");
			mail.setTemplate("email-template");
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("Nome", usuario.getNomeUsuario());
			model.put("Sistema","http://localhost:3000");
			model.put("url","http://localhost:3000/update/senha/"+encodedToken);
			model.put("texto", "Você solicitou a alteração da senha no sistema, acesse o link para continuar. ");
		    mail.setModel(model);
		    
		    sendEmail.createEmail(mail);
		} catch (UnsupportedEncodingException e) {

			 throw new NegocioException(FormatMessage.formatMessage("Erro na codificação do token "));
		}
		 
		
	}

	@Override
	public void changePassword(UpdatePasswordRequest updatePasswordRequest) {
        
		ValidarDados.confirmSenhaIsValid(updatePasswordRequest.getSenha(), updatePasswordRequest.getConfirmSenha());
		
		String token = updatePasswordRequest.getToken();
		
		String email = jwtService.getEmail(token);
		
		Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(
				() -> new UserNotFoundException(FormatMessage.formatMessage("Usuário não cadastrado")));
		

	    if (!jwtService.verifyToken(token)) {
			throw new AccessForbiddenException(FormatMessage.formatMessage("Token inválido"));
		}
		
	    try {
			usuarioRepository.updatePassword(usuario.getIdUsuario(), crypt.encryptPassword(updatePasswordRequest.getSenha()));
		} catch (Exception e) {
            throw new NegocioException(FormatMessage.formatMessage("Erro na alteração da senha do usuário com e-mail %s ",usuario.getEmail()));
        }
	    
	    Mail mail = new Mail();
		
		mail.setTo(usuario.getEmail());
		mail.setFrom("no-reply@academico.edu.br");
		mail.setSubject("Senha alterada no sistema ");
		mail.setTemplate("login-template");
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("Nome", usuario.getNomeUsuario());
		model.put("Sistema","http://localhost:3000");
		model.put("url","http://localhost:3000/login");
	    
		mail.setModel(model);
		
		sendEmail.createEmail(mail);
		
	}

	@Override
	public boolean validateToken(String token) {
		if (!jwtService.verifyToken(token)) {
			throw new AccessForbiddenException(FormatMessage.formatMessage("Token inválido, acesso negado"));
		}
		return true;
	}

}
