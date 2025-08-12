package com.academico.config;

//import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SpringApiConfig {
	
	@Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI().info(apiInfo())
							.externalDocs( new ExternalDocumentation().description("IFSP").url("http://bri.ifsp.edu.br"));
//							.tags(Arrays.asList(
//									new Tag().name("Role").description("Gerencia a roles do sistema"),
//									new Tag().name("Usuário").description("Gerencia o cadastro dos usuários no sistema"),
//									new Tag().name("Aluno").description("Gerencia o cadastro dos alunos no sistema"),
//									new Tag().name("Avaliação").description("Gerencia o cadastro das avaliações no sistema"),
//									new Tag().name("Cidade").description("Gerencia o cadastro de cidades sistema"),
//									new Tag().name("Disciplina").description("Gerencia o cadastro das disciplinas no sistema"),
//									new Tag().name("Professor").description("Gerencia o cadastro dos professores no sistema"),
//									new Tag().name("Permissões").description("Gerencia o cadastro das permissões de usuário no sistema"),
//									new Tag().name("Usuário").description("Gerencia o cadastro dos usuários no sistema"))
//							);
	}

	private Info apiInfo() {
		return new Info().title("RESTful API with Java 19 and Spring Boot 3.1.2").version("v1")
						 .description("Descrição dos Recursos oferecidos pela API")
						 .termsOfService("http://ppwi4.bri.ifsp.edu.br")
						 .license(
								 new License().name("Apache 2.0")
						   	 			      .url("https://www.apache.org/licenses/LICENSE-2.0")
						 );
	
	}

}
