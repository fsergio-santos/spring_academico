package com.academico.config;

import java.util.Set;

public class ProjectConfig {

	public static final String DIR_RELATORIOS = "/relatorios/";
	
	public static final String DIRETORIO_FOTOS = "/arquivos/nds/files/catalogo";
	
	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE   = "10";
	public static final String PROPS = "id";
	public static final String DIR = "asc";
	
	public static final String SCHEMA_DEFAULT = "PROJETO23";
	
	public static final String TOKEN_INVALIDO = "invalido";
    public static final String TOKEN_EXPIRADO = "expirado";
    public static final String TOKEN_VALIDO   = "valido";
	
    public static final String EMAIL_ENVIO = "no-reply@academico.edu.br";
    
    public static final String URL_CLIENTE = "http://localhost:3000";
    public static final String URL_SERVIDOR = "http://localhost:8080";
    public static final Long   MAX_AGE = 3600L;
    public static final String SECRETKEY = "u4eN60NmnVo1wcPSzLINegXslwAR3jb2NtUV174rV38";
    
    public static final String NUMBERS = "0123456789";
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String SPECIAL_CHARACTERS = "!'^+%&/()=?_#$½§{[]}|;:>÷`<.*-@é";
    
	public static final int MILISEGUNDOS = 1000; // 1 SEGUNDO
	public static final int SEGUNDOS = 60;       // 1 MINUTO
	public static final int HORAS = 60;          // 1 HORAS
	public static final int DIA   = 24;          // 1 DIA
	
	public static final Set<Integer> VALORES_VALIDOS = Set.of(1, 2);
	
    public static final String[] 
    	WHITE_LIST_URL = {
    		"/v3/api-docs",
			"/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    	};
    
}
