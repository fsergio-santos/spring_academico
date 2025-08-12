package com.academico.models.service.security;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.academico.models.model.RolePermission;
import com.academico.models.repository.RolePermissionRepository;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	@Autowired
    private RolePermissionRepository rolePermissionRepository;	

	@Override
	public boolean hasPermission(Authentication auth, Object model, Object permission) {
		
		if ( (auth == null )  || !( model instanceof String )  || !(permission instanceof String )  ) {
			return false;
		}
		
		return hasPermissions(auth,  model.toString().toUpperCase(), permission.toString().toUpperCase());
	}


	@Override
	public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
	    return Optional.ofNullable(auth)
	            .filter(a -> targetType != null && targetId != null && permission instanceof String)
	            .map(a -> (Boolean) hasPermissions(a, targetType.toUpperCase(), permission.toString().toUpperCase()))
	            .orElse(false);
	}


	
	
	private boolean hasPermissions(Authentication auth, String model, String permission) {
		
		List<String> havePermissions = extractRoles(auth);
	
		List<RolePermission> rolePermissions = rolePermissionRepository.findPermissions(havePermissions, model);
		
        if (rolePermissions.isEmpty()) {
            return false;
        }


        for (RolePermission rolePermission : rolePermissions) {
            boolean isGranted = switch (permission) {
                case "READ":   yield rolePermission.getRead() == 1 ? true : false;
                case "CREATE": yield rolePermission.getCreate() == 1 ? true : false;
                case "UPDATE": yield rolePermission.getUpdate() == 1 ? true : false;
                case "DELETE": yield rolePermission.getDelete() == 1 ? true : false;
                default: yield false;
            };

            if (Boolean.TRUE.equals(isGranted)) {
                return true;
            }
        }

        return false;

	}
	
	private List<String> extractRoles(Authentication auth) {
	    return auth.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());
	}


}
