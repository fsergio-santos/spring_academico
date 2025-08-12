package com.academico.web.swagger;

import org.springframework.http.ResponseEntity;

import com.academico.models.service.dto.request.CidadeRequest;
import com.academico.models.service.dto.response.CidadeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Cidade", description = "Endpoints para gerenciar os dados do cidade")
public interface CidadeControllerApi {

	@Operation(summary = "Relatório de todos os cidades cadastrados no sistema - paginação no cliente", description = "Relatório de todos os cidades", responses = {
			@ApiResponse(
					responseCode = "200", 
					content = {
					   @Content(
							mediaType = "application/json", 
							array = @ArraySchema(schema = @Schema(implementation = CidadeResponse.class))) 
			}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<?> listar(HttpServletRequest request);
	
	@Operation(
			summary = "Cadastra um cidade", 
			description = "necessita de todos os dados válidos", 
			responses = {
				@ApiResponse(
					responseCode = "200",
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = CidadeResponse.class))
			    ),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public ResponseEntity<?> save(
			@RequestBody(description = "Representação de uma novo cidade para cadastrar no sistema", required = true) 
			CidadeRequest cidadeRequest, 
			HttpServletRequest request);

	@Operation(
			summary = "Altera o Cadastro de um cidade", 
			description = "necessita de todos os dados válidos", 
			responses = {
				@ApiResponse(
					responseCode = "200",
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = CidadeResponse.class))
			    ),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public ResponseEntity<?> update(
			@Parameter(description = "ID de um cidade", example = "1", required = true) 
			Long id, 
			@RequestBody(description = "Representação de um cidade cadastrado no sistema", required = true) 
			CidadeRequest cidadeRequest, 
			HttpServletRequest request);

	@Operation(
			summary = "Pesquisa as informações de cidade por id para esclusão do registro",
			description = "Necessita de im identificador único válido",
			responses = {
				@ApiResponse(
						responseCode = "200", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = CidadeResponse.class))
						),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
	})
	public ResponseEntity<?> deleteById(
			@Parameter(description = "ID de um cidade", example = "1", required = true) 
			Long id, 
			HttpServletRequest request);

	
	@Operation(
			summary = "Pesquisa as informações de cidade por id ", 
			description = "Necessita de um identificador único válido",
			responses = {
				@ApiResponse(
						responseCode = "200", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = CidadeResponse.class))
					),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), 
	})
	public ResponseEntity<?> findById(
			@Parameter(description = "ID de um cidade", example = "1", required = true) 
			Long id,
			HttpServletRequest request);

	
	

	

}