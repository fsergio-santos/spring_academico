package com.academico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class SpringWebConfig implements WebMvcConfigurer {
	
    
	
	@Override 
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")
		         .allowedOrigins(
		        		 SpringProjectConfig.URL_CLIENTE,
		        		 SpringProjectConfig.URL_SERVIDOR
		         ).allowedHeaders(
		 				 HttpHeaders.AUTHORIZATION,
		 				 HttpHeaders.ACCEPT,
		 				 HttpHeaders.CONTENT_TYPE
		 		 ).allowedMethods(
		 				 HttpMethod.GET.name(),
		 				 HttpMethod.POST.name(),
		 				 HttpMethod.PUT.name(),
		 				 HttpMethod.PATCH.name(),
		 				 HttpMethod.DELETE.name(),
		 				 HttpMethod.OPTIONS.name()
		 		 ).allowCredentials(true)
		          .maxAge(SpringProjectConfig.MAX_AGE);
		
	 
	 }

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false)
			      .ignoreAcceptHeader(false)
				  .useRegisteredExtensionsOnly(false)
				  .defaultContentType(MediaType.APPLICATION_JSON)
				  .mediaType("json", MediaType.APPLICATION_JSON)
				  .mediaType("xml", MediaType.APPLICATION_XML);
	}

   
	

}
