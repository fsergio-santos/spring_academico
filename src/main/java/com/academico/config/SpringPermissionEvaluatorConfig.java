package com.academico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.academico.models.service.security.CustomPermissionEvaluator;

@Configuration
@EnableMethodSecurity(prePostEnabled = false)
public class SpringPermissionEvaluatorConfig {
	
			
//	@Bean
//	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//	Advisor preAuthorizeAuthorizationMethodInterceptor(CustomPermissionEvaluator customPermissionEvaluator) {
//		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//		expressionHandler.setPermissionEvaluator(customPermissionEvaluator);
//		PreAuthorizeAuthorizationManager authorizationManager = new PreAuthorizeAuthorizationManager();
//		authorizationManager.setExpressionHandler(expressionHandler);
//
//		return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(authorizationManager);
//	}
	
	@Bean
	CustomPermissionEvaluator customPermissionEvaluator() {
		return new CustomPermissionEvaluator();
	}

}
