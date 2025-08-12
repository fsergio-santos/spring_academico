package com.academico.models.service.dto.validation;

import com.academico.models.service.dto.request.UsuarioRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsuarioValidator implements ConstraintValidator<UsuarioValidation, UsuarioRequest>{

	@Override
	public boolean isValid(UsuarioRequest usuario, ConstraintValidatorContext context) {
		
		if (usuario == null ) return false;
		
		boolean isProfessor = usuario.getTipo() == 1;
		boolean isAluno = usuario.getTipo() == 2;
		boolean isValid = true;
		
		if (isProfessor) {
			if (isEmpty(usuario.getNomeProfessor())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("O nome do professor deve ser informado")
				       .addPropertyNode("nomeProfessor") 
				       .addConstraintViolation();
			} else if (isEmpty(usuario.getCodProfessor()) ){
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("O código do professor deve ser informado")
				       .addPropertyNode("codProfessor")        
			           .addConstraintViolation();
			}
			isValid = false;
		} else if (isAluno) {
			if (isEmpty(usuario.getNomeAluno())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("O nome do aluno deve ser informado")
				       .addPropertyNode("nomeAluno") 
				       .addConstraintViolation();
			} else if (isEmpty(usuario.getIdade().toString()) ){
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("A idade do aluno deve ser informada")
				       .addPropertyNode("idade") 
				       .addConstraintViolation();
			}
			isValid = false;
		} else {
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tipo de usuário inválido").addConstraintViolation();
            isValid = false;
		}
		
		return isValid;
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

}
