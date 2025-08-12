package com.academico.config;

import java.util.Set;



public class SpringProjectConfig {

	public static final String DIR_RELATORIOS = "/relatorios/";

	public static final String DIRETORIO_FOTOS = "/arquivos/nds/files/catalogo";

	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "10";
	public static final String PROPS = "id";
	public static final String DIR = "asc";

	public static final String SCHEMA_DEFAULT = "PROJETO23";

	public static final String EMAIL_ENVIO = "no-reply@academico.edu.br";

	public static final String URL_CLIENTE = "http://localhost:3000";
	public static final String URL_SERVIDOR = "http://localhost:8080";
	public static final Long MAX_AGE = 3600L;
	public static final String SECRETKEY = "u4eN60NmnVo1wcPSzLINegXslwAR3jb2NtUV174rV38";

	public static final String NUMBERS = "0123456789";
	public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	public static final String SPECIAL_CHARACTERS = "!'^+%&/()=?_#$½§{[]}|;:>÷`<.*-@é";

	public static final int MILISEGUNDOS = 1000; // 1 SEGUNDO
	public static final int SEGUNDOS = 60; // 1 MINUTO
	public static final int HORAS = 60; // 1 HORAS
	public static final int DIA = 24; // 1 DIA

	public static final long REGISTER_TOKEN_EXPIRATION = 5 * 60 * 1000;
	public static final long ACCESS_TOKEN_EXPIRATION = 30 * 60 * 1000;
	public static final long REFRESH_TOKEN_EXPIRATION = 2 * 60 * 60 * 1000;

	public static final String ACCESS_TOKEN = "access_token";
	public static final String REFRESH_TOKEN = "refresh_token";

	public static final Set<Integer> VALORES_VALIDOS = Set.of(1, 2);

	public static final String[] WHITE_LIST_URL = { 
			"/docs/index.html",
            "/docs-park.html", 
            "/docs-park/**",
            "/v3/api-docs/**",
            "/swagger-ui-custom.html", 
            "/swagger-ui.html", 
            "/swagger-ui/**",
            "/**.html", 
            "/webjars/**", 
            "/configuration/**", 
            "/swagger-resources/**"
	};

	public static final String[][] END_POINTS_WITH_AUTHENTICATION_NOT_REQUIRED = { 
			{"POST","/rest/login"}, 
		    {"POST","/rest/checkopt"},
			{"GET","/rest/code/{email}"}, 
			{"POST","/rest/registrar/salvar"}, 
			{"GET","/rest/registrar/ativar"}, 
			{"GET","/rest/update/senha/{email}"},
			{"POST","/rest/update/password"},
			{"GET", "/rest/update/validatetoken/{token}"},
			{"GET", "/rest/rolepermission/listar"},
			{"GET", "/rest/rolepermission/buscar/{id}"},
	};
	
	public static final String[][] END_POINTS_WITH_ADMIN_MANAGER_USER = {
			{"GET","/rest/usuario/listar"},
			{"GET","/rest/usuario/listagem"},
			{"GET","/rest/usuario/pagination"},
			{"GET","/rest/usuario/buscar/{id}"},
			{"GET","/rest/usuario/totais"},
			{"GET","/rest/usuario/buscarusuario/{id}"},
			{"GET","/rest/role/listar"},
			{"GET","/rest/role/buscar/{id}"},
			{"POST","/rest/refreshtoken"},
			{"GET","/rest/professor/listar"},
			{"GET","/rest/professor/buscar/{id}"},
			{"GET","/rest/permissao/listar"},
			{"GET","/rest/permissao/buscar/{id}"},
			{"GET","/rest/disciplina/listar"},
			{"GET","/rest/disciplina/buscar/{id}"},
			{"GET","/rest/cidade/listar"},
			{"GET","/rest/cidade/buscar/{id}"},
			{"GET","/rest/avaliacao/listar"},
			{"GET","/rest/avaliacao/buscar/{id}"},
			{"GET","/rest/aluno/listar"},
			{"GET","/rest/aluno/buscar/{id}"},
			
	};
	
	public static final String[][] END_POINTS_WITH_ADMIN_MANAGER = {
			{"POST","/rest/usuario/salvar"},
			{"PUT","/rest/usuario/alterar/{id}"},
			{"POST","/rest/role/salvar"},
			{"PUT","/rest/role/alterar/{id}"},
			{"POST","/rest/professor/salvar"},
			{"PUT","/rest/professor/alterar/{id}"},
			{"POST","/rest/permissao/salvar"},
			{"PUT","/rest/permissao/alterar/{id}"},
			{"POST","/rest/disciplina/salvar"},
			{"PUT","/rest/disciplina/alterar/{id}"},
			{"POST","/rest/cidade/salvar"},
			{"PUT","/rest/cidade/alterar/{id}"},
			{"POST","/rest/avaliacao/salvar"},
			{"PUT","/rest/avaliacao/alterar/{id}"},
			{"POST","/rest/aluno/salvar"},
			{"PUT","/rest/aluno/alterar/{id}"},
			
	};
	
	public static final String[][] END_POINTS_WITH_ADMIN = {
			{"DELETE","/rest/usuario/excluir/{id}"},
			{"DELETE","/rest/role/excluir/{id}"},
			{"DELETE","/rest/professor/excluir/{id}"},
			{"DELETE","/rest/permissao/excluir/{id}"},
			{"DELETE","/rest/disciplina/excluir/{id}"},
			{"DELETE","/rest/cidade/excluir/{id}"},
			{"DELETE","/rest/avaliacao/excluir/{id}"},
			{"DELETE","/rest/aluno/excluir/{id}"},
			
	};
}
