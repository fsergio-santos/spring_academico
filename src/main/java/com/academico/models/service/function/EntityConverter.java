package com.academico.models.service.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EntityConverter {
	
	public static <S, D> D convert(S source, Class<D> destinationClass) {
        if (source == null) {
            return null;
        }
        try {
            D destination = destinationClass.getDeclaredConstructor().newInstance();
            copyFields(source, destination);
            return destination;
        } catch (Exception e) {
            throw new RuntimeException("Error converting object", e);
        }
    }
	
	
	public static <S, D> List<D> convert(List<S> sourceList, Class<D> destinationClass) {
        if (sourceList == null) {
            return null;
        }
        List<D> destinationList = new ArrayList<>();
        for (S source : sourceList) {
            destinationList.add(convert(source, destinationClass));
        }
        return destinationList;
    }

    public static <S, D> Set<D> convert(Set<S> sourceSet, Class<D> destinationClass) {
        if (sourceSet == null) {
            return null;
        }
        Set<D> destinationSet = new HashSet<>();
        for (S source : sourceSet) {
            destinationSet.add(convert(source, destinationClass));
        }
        return destinationSet;
    }
    
    public static <S, D> Optional<D> convert(Optional<S> sourceOptional, Class<D> destinationClass) {
        if (sourceOptional == null) {
            return Optional.empty();
        }
        return sourceOptional.map(source -> convert(source, destinationClass));
    }
    
    
    public static <T> Optional<T> convert(T object) {
        return Optional.ofNullable(object);
    }
    
    
    private static void copyFields(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Field targetField = target.getClass().getDeclaredField(field.getName());
                targetField.setAccessible(true);
                Object value = field.get(source);
                if (value != null ) {
                   targetField.set(target, field.get(source));
                }   
            } catch (NoSuchFieldException | IllegalAccessException e) {
               continue;
            }
        }
    }
	

}
