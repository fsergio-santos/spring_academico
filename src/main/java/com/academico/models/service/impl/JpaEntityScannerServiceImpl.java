package com.academico.models.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.models.enums.RoleName;
import com.academico.models.model.RolePermission;
import com.academico.models.repository.RolePermissionRepository;
import com.academico.models.service.JpaEntityScannerService;
import com.academico.models.service.dto.converter.RolePermissionConverter;
import com.academico.models.service.dto.response.RolePermissionResponse;
import com.academico.models.service.function.JpaEntityScanner;

@Service
public class JpaEntityScannerServiceImpl implements JpaEntityScannerService{

	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public List<RolePermissionResponse> listarEntidadesRolePermissao() {
		
		List<String> entidades = JpaEntityScanner.getJpaEntity();
		
		List<RolePermissionResponse> listaFinal = new ArrayList<>();
		
		List<RolePermission> rolePermissionExistentes = rolePermissionRepository.findAll();
	    
		
		Map<String, RolePermission> mapaRolePermissionExistentes = 
				 rolePermissionExistentes.stream().collect(Collectors.toMap(p->p.getRole()+"_"+p.getTarget(), p->p));
		
		List<RolePermission> novasPermissoes = new ArrayList<>();
		
		for(String entidade: entidades ) {
	
			for (String role : RoleName.listRoles()) {

				RolePermission rolePermission = new RolePermission();
				
				String chave = role+"_"+entidade;
			    
				RolePermissionResponse rolePermissionResponse = null;
				
				if (mapaRolePermissionExistentes.containsKey(chave)) {
				
					rolePermissionResponse = RolePermissionConverter.toRolePermissionResponse(mapaRolePermissionExistentes.get(chave));
				
				} else {
					rolePermission.setRole(role);
					rolePermission.setTarget(entidade);
					rolePermission.setCreate(0);
					rolePermission.setDelete(0);
					rolePermission.setRead(0);
					rolePermission.setUpdate(0);
					rolePermissionResponse = RolePermissionConverter.toRolePermissionResponse(rolePermission);
					
					novasPermissoes.add(rolePermission);
				}
				
				listaFinal.add(rolePermissionResponse);
				
			}
		}
		
		if (!novasPermissoes.isEmpty()) {
			rolePermissionRepository.saveAll(novasPermissoes);
		}
		
		return listaFinal;
		
		
		
	}

	
	
	
	
}
