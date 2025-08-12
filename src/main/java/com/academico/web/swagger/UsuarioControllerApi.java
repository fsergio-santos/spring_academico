package com.academico.web.swagger;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.models.service.dto.response.UsuarioResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Usuário", description = "Endpoints para gerenciar os dados do usuário")
public interface UsuarioControllerApi {

	@Operation(summary = "Relatório de todos os usuários cadastrados no sistema - paginação no cliente", description = "Relatório de todos os usuários", responses = {
			@ApiResponse(
					responseCode = "200", 
					content = {
					   @Content(
							mediaType = "application/json", 
							array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class))) 
			}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<?> listar(HttpServletRequest request);

	@Operation(
			summary = "Relatório de todos os usuários cadastrados no sistema - paginação no servidor", 
			description = "Relatórios de todos os usuários", 
			responses = {
			@ApiResponse(
					responseCode = "200", 
					content = {
					    @Content(
					    		mediaType = "application/json", 
					    		array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class))) 
					}
			 ),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
		})
	public ResponseEntity<?> listaPaginada(
			@Parameter(description = "Descrição dos dados do usuário") String key,
			@Parameter(description = "Número da página atual", example = "1") Integer page,
			@Parameter(description = "Número da registros por página ", example = "10") Integer pageSize,
			@Parameter(description = "Classificação da Página", example = "asc") String dir,
			@Parameter(description = "Atributo para classificar a página", example = "id") String props,
			HttpServletRequest request);
	
	@Operation(summary = "Relatório de todos os usuários cadastrados no sistema - paginação no servidor", description = "Relatórios de todos os usuários", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(
							mediaType = "application/json", 
							array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class))) 
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
		})
	public ResponseEntity<?> getUsuarioPagination(
			@Parameter(description = "Descrição dos dados do usuário") String key,
			@Parameter(description = "Número da página atual", example = "1") Integer page,
			@Parameter(description = "Número da registros por página ", example = "10") Integer pageSize,
			@Parameter(description = "Classificação da Página", example = "asc") String dir,
			@Parameter(description = "Atributo para classificar a página", example = "id") String props,
			HttpServletRequest request);

	
	@Operation(
			summary = "Cadastra um usuário", 
			description = "necessita de todos os dados válidos", 
			responses = {
				@ApiResponse(
					responseCode = "200",
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = UsuarioResponse.class))
			    ),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public ResponseEntity<?> save(
			@RequestBody(description = "Representação de uma novo usuário para cadastrar no sistema", required = true) 
			UsuarioRequest usuarioRequest, 
			HttpServletRequest request);

	@Operation(
			summary = "Altera o Cadastro de um usuário", 
			description = "necessita de todos os dados válidos", 
			responses = {
				@ApiResponse(
					responseCode = "200",
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = UsuarioResponse.class))
			    ),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public ResponseEntity<?> update(
			@Parameter(description = "ID de um usuário", example = "1", required = true) 
			Long id, 
			@RequestBody(description = "Representação de um usuário cadastrado no sistema", required = true) 
			UsuarioRequest usuarioRequest, 
			HttpServletRequest request);

	@Operation(
			summary = "Altera o Cadastro de um usuário", 
			description = "necessita de todos os dados válidos", 
			responses = {
				@ApiResponse(
					responseCode = "200",
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = UsuarioResponse.class))
			    ),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public ResponseEntity<?> updateField(
			@Parameter(description = "ID de um usuário", example = "1", required = true)
			Long id, 
			@RequestBody(description = "Representação dos campos a serem alterados do usuário ", required = true) 
			Map<String, Object> dataRequest, HttpServletRequest request);

	@Operation(
			summary = "Pesquisa as informações de usuário por id para esclusão do registro",
			description = "Necessita de im identificador único válido",
			responses = {
				@ApiResponse(
						responseCode = "200", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = UsuarioResponse.class))
						),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
	})
	public ResponseEntity<?> deleteById(
			@Parameter(description = "ID de um usuário", example = "1", required = true) 
			Long id, 
			HttpServletRequest request);

	
	@Operation(
			summary = "Pesquisa as informações de usuário por id ", 
			description = "Necessita de um identificador único válido",
			responses = {
				@ApiResponse(
						responseCode = "200", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = UsuarioResponse.class))
					),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
	})
	public ResponseEntity<?> findById(
			@Parameter(description = "ID de um usuário", example = "1", required = true) 
			Long id,
			HttpServletRequest request);

	
	@Operation(
			summary = "Pesquisa as informações de usuários cadastrados no sistema  ", 
			responses = {
				@ApiResponse(
						responseCode = "200", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = UsuarioResponse.class))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
	})
	public ResponseEntity<?> showTotalUsuarios(HttpServletRequest request);

	@Operation(
			summary = "Pesquisa as informações de usuários cadastrados no sistema  ", 
			description = "Necessita de um identificador único válido",
			responses = {
				@ApiResponse(
						responseCode = "200", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = UsuarioResponse.class))
						),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
	})
	public ResponseEntity<?> findUsuarioById(
			@Parameter(description = "ID de um usuário", example = "1", required = true) 
			Long id, 
			HttpServletRequest request);

}