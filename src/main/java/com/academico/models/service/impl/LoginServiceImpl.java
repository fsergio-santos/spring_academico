package com.academico.models.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.config.SpringProjectConfig;
import com.academico.models.model.Usuario;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.JwtService;
import com.academico.models.service.LoginService;
import com.academico.models.service.components.CriptografarSenha;
import com.academico.models.service.components.OtpGenerate;
import com.academico.models.service.components.SendEmail;
import com.academico.models.service.dto.converter.UsuarioConverter;
import com.academico.models.service.dto.mail.Mail;
import com.academico.models.service.dto.request.LoginRequest;
import com.academico.models.service.dto.request.LoginVerifyOtp;
import com.academico.models.service.dto.response.LoginResponse;
import com.academico.models.service.exception.InvalidPasswordException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.exception.OtpCodeException;
import com.academico.models.service.exception.UserLockedException;
import com.academico.models.service.exception.UserNotFoundException;
import com.academico.models.service.function.FormatMessage;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CriptografarSenha crypt;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private SendEmail sendEmail;

	protected OtpGenerate opt = new OtpGenerate();

	@Override
	public LoginResponse login(LoginRequest loginRequest) {

		Usuario usuario = findUsuarioByEmail(loginRequest.getEmail());

		if (usuario.getEmail().equals(loginRequest.getEmail()) && usuario.isAtivo() == false) {
			throw new UserLockedException(FormatMessage.formatMessage("Usuário está bloqueado no sistema"));
		}

		if (usuario.getEmail().equals(loginRequest.getEmail())
				&& !crypt.verifyPassword(loginRequest.getSenha(), usuario.getSenha())) {
			throw new InvalidPasswordException("Senha inválida ou está incorreta!");
		}

		String access_token = jwtService.generateAccessToken(usuario);
		String refresh_token = jwtService.generateRefreshToken(usuario);

		List<String> roles = usuario.getUsuarios()
				                    .stream()
				                    .map(urp -> urp.getRole().getNomeRole().getNome())
				                    .collect(Collectors.toSet())
									.stream()
		                            .collect(Collectors.toList());

		List<String> permissions = usuario.getUsuarios()
				                          .stream()
				                          .map(urp -> urp.getPermission().getNomePermission().getNomePermissions())
				                          .collect(Collectors.toSet())
										  .stream()
				                          .collect(Collectors.toList());
		
		return UsuarioConverter.usuarioToLoginResponse(usuario, access_token, refresh_token, roles, permissions );
	}

	@Override
	public void checkEmailOtp(LoginVerifyOtp loginVerifyOtp) {

		Usuario usuario = findUsuarioByEmail(loginVerifyOtp.getEmail());

		opt.setOtpValue(usuario.getOtp());
		opt.setTimeStamp(usuario.getOtpExpiry());

		if (opt.validate(loginVerifyOtp.getOtp(), LocalDateTime.now()) == false) {
			throw new OtpCodeException(FormatMessage.formatMessage("Código fornecido é inválido"));
		}
		;

	}

	@Override
	public void rquestOtp(String email) {

		Usuario usuario = findUsuarioByEmail(email);

		OtpGenerate opt = new OtpGenerate(usuario.getEmail());

		Mail mail = new Mail();

		try {
			usuarioRepository.updateOpt(opt.getOtpValue(), opt.getTimeStamp(), usuario.getIdUsuario());
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário %s", usuario.getEmail()));
		}

		mail.setTo(usuario.getEmail());
		mail.setFrom(SpringProjectConfig.EMAIL_ENVIO);
		mail.setSubject("Autorização para acesso ao sistema ");
		mail.setTemplate("otp-template");

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("Nome", usuario.getNomeUsuario());
		model.put("Token", opt.getOtpValue());

		mail.setModel(model);

		sendEmail.createEmail(mail);

	}

	@Override
	public void logout(String email) {

		Usuario usuario = findUsuarioByEmail(email);

		try {
			usuarioRepository.updateOpt(null, null, usuario.getIdUsuario());
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário %s", usuario.getEmail()));
		}

	}

	protected Usuario findUsuarioByEmail(String email) {
		Usuario usuario = usuarioRepository.findUsuarioByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(FormatMessage.formatMessage("Usuário não cadastrado")));
		return usuario;
	}

}
