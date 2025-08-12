package com.academico.models.service.dto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = UsuarioValidator.class)
public @interface UsuarioValidation {

	String message() default "Dados inconsistentes para o tipo de Usuário";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
