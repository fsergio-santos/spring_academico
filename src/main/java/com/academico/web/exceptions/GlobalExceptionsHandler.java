package com.academico.web.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.academico.models.service.dto.response.FieldsResponse;
import com.academico.models.service.exception.AccessForbiddenException;
import com.academico.models.service.exception.BadCredentialsException;
import com.academico.models.service.exception.EmailJaCadastradoException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.exception.OtpCodeException;
import com.academico.models.service.exception.UntypedFieldsException;
import com.academico.models.service.exception.UserLockedException;
import com.academico.models.service.exception.UserNotFoundException;
import com.academico.web.response.MensagemSistema;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.academico")
public class GlobalExceptionsHandler {
	
	    @Autowired
		private MessageSource messageSource;
	
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) 
	    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
	    	List<FieldsResponse> listaErrors = ex.getBindingResult()
		                            .getFieldErrors()
					                .stream()
					                .map(fieldError -> {
					                     FieldsResponse fields = new FieldsResponse();
						                 fields.setNome(fieldError.getField()); 
						                 fields.setMensagem(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())); 
						                 return fields; 
						             }).collect(Collectors.toList());

			return MensagemSistema.showMensagem("Erros na digitação dos campos ",HttpStatus.UNPROCESSABLE_ENTITY, listaErrors, request);
	    }

	    @ExceptionHandler(EmailJaCadastradoException.class)
	    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) 
	    public ResponseEntity<?> handleGeneralExceptions(EmailJaCadastradoException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, request);
	    }
	    
	    @ExceptionHandler(UserNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND) 
	    public ResponseEntity<?> handleGeneralExceptions(UserNotFoundException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.NOT_FOUND, request);
	    }
	    
	    @ExceptionHandler(UserLockedException.class)
	    @ResponseStatus(HttpStatus.FORBIDDEN) 
	    public ResponseEntity<?> handleGeneralExceptions(UserLockedException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.FORBIDDEN, request);
	    }
	    
	    @ExceptionHandler(BadCredentialsException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) 
	    public ResponseEntity<?> handleGeneralExceptions(BadCredentialsException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    @ExceptionHandler(OtpCodeException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) 
	    public ResponseEntity<?> handleGeneralExceptions(OtpCodeException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    @ExceptionHandler(UntypedFieldsException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) 
	    public ResponseEntity<?> handleGeneralExceptions(UntypedFieldsException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	    }
	   
	    @ExceptionHandler(NegocioException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) 
	    public ResponseEntity<?> handleGeneralExceptions(NegocioException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    @ExceptionHandler(IllegalArgumentException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) 
	    public ResponseEntity<?> handleGeneralExceptions(IllegalArgumentException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    
	    @ExceptionHandler(AccessForbiddenException.class)
	    @ResponseStatus(HttpStatus.FORBIDDEN) 
	    public ResponseEntity<?> handleGeneralExceptions(AccessForbiddenException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.FORBIDDEN, request);
	    }
	    
	    @ExceptionHandler(AuthorizationDeniedException.class)
	    @ResponseStatus(HttpStatus.FORBIDDEN) 
	    public ResponseEntity<?> handleGeneralExceptions(AuthorizationDeniedException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.FORBIDDEN, request);
	    }
	    
	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) 
	    public ResponseEntity<?> handleGeneralExceptions(RuntimeException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
	    public ResponseEntity<?> handleGeneralExceptions(DataIntegrityViolationException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	    }
	    
	    	   
	    @ExceptionHandler(DataAccessException.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
	    public ResponseEntity<?> handleGeneralExceptions(DataAccessException ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	    }
	    
	    
	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
	    public ResponseEntity<?> handleGeneralExceptions(Exception ex, HttpServletRequest request) {
	        return MensagemSistema.showMensagem(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	    } 
	    
}
