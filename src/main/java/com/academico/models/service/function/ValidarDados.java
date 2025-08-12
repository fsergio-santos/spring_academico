package com.academico.models.service.function;

import java.util.Set;
import java.util.stream.Collectors;

import com.academico.config.SpringProjectConfig;
import com.academico.models.service.exception.InvalidPasswordException;
import com.academico.models.service.exception.NotNumberException;

public class ValidarDados {

	
    public static boolean validarId(Long id, String texto ) {
    	
    	if (id == null || !(id instanceof Number)) {
            throw new NotNumberException(
            		FormatMessage.formatMessage("O id informado não é válido: %s (%s)", id, texto));
        }
        
        String idStr = id.toString();
        if (idStr.isBlank() || idStr.isEmpty()) {
            throw new IllegalArgumentException(
            		FormatMessage.formatMessage("O id = %s não foi informado (%s)", id, texto));
        }
        
        return true;
	}
    
    
    public static boolean validarId(Long id ) {
    	
    	if (id == null || !(id instanceof Number)) {
            throw new NotNumberException(
            		FormatMessage.formatMessage("O id informado não é válido: %s (%s)", id));
        }
        
        String idStr = id.toString();
        if (idStr.isBlank() || idStr.isEmpty()) {
            throw new IllegalArgumentException(
                    FormatMessage.formatMessage("O id = %s não foi informado (%s)", id));
        }
        
        return true;
	}
   
     
	public static boolean isNumber(Object obj) {
        if (!(obj instanceof Number)) {
            throw new NotNumberException(
            		FormatMessage.formatMessage("O valor passado não é um número: " + obj.getClass().getName()));
        }
        return true;
    }
	
	
	public static void confirmSenhaIsValid(String senha, String confirmSenha) {

		if (!senha.equals(confirmSenha)) {
			throw new InvalidPasswordException(FormatMessage.formatMessage("As senhas informadas não coincidem"));
		}

	} 
	
	
	public static  void validarSenha(String password) {
		
		 Set<Character> passwordChars = getCharacter(password);
		 
		 if (!containsCharacter(passwordChars, SpringProjectConfig.NUMBERS)) {
			 throw new InvalidPasswordException(FormatMessage.formatMessage("A senha deve conter pelo menos um número"));
		 };
		 
		 if (!containsCharacter(passwordChars, SpringProjectConfig.UPPERCASE_LETTERS)) {
			 throw new InvalidPasswordException(FormatMessage.formatMessage("A senha deve conter pelo menos uma letra maiúscula" ));
		 };
		 
		 if (!containsCharacter(passwordChars, SpringProjectConfig.LOWERCASE_LETTERS)) {
			 throw new InvalidPasswordException(FormatMessage.formatMessage("A senha deve conter pelo menos uma letra minúscula"));
		 };
		 
		 if (!containsCharacter(passwordChars, SpringProjectConfig.SPECIAL_CHARACTERS)) {
			 throw new InvalidPasswordException(FormatMessage.formatMessage("A senha deve conter pelo menos um caracter especial"));
		 };
		 
		 
	}
	
	private static  Set<Character> getCharacter(String password){
		return password.chars()
                       .mapToObj(c -> (char) c)
                       .collect(Collectors.toSet());
	}
	
	
	private static boolean containsCharacter(Set<Character> passwordChars, String characters) {
        for (char c : characters.toCharArray()) {
            if (passwordChars.contains(c)) {
                return true;
            }
        }
        return false;
    }
}
