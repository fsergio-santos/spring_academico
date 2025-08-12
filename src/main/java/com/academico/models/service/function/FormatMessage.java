package com.academico.models.service.function;

public class FormatMessage {
	
	public static String formatMessage(String message, Object... args) {
		return String.format(message, args);
		
	}
	
	public static String formatMessage(String message) {
		return String.format(message);
		
	}

}
