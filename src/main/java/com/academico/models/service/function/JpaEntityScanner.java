package com.academico.models.service.function;

import java.util.List;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import jakarta.persistence.Entity;
import java.util.Set;
import java.util.stream.Collectors;


public class JpaEntityScanner {
	
	private static String PKG = "com.academico.models.model";
	
	public static List<String> getJpaEntity(){
		
		Reflections reflexions = new Reflections(PKG, Scanners.TypesAnnotated);
		
		Set<Class<?>> entityClasses = reflexions.getTypesAnnotatedWith(Entity.class);
		
		return entityClasses.stream()
				            .map(Class::getSimpleName)
				            .collect(Collectors.toList());		
		
	}

}
