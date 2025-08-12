package com.academico.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.RolePermission;
import com.academico.models.repository.RolePermissionRepository;
import com.academico.models.service.RolePermissionService;
import com.academico.models.service.dto.converter.RolePermissionConverter;
import com.academico.models.service.dto.request.RolePermissionRequest;
import com.academico.models.service.dto.response.RolePermissionResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	
	@Override
	public RolePermissionResponse update(Long id, RolePermissionRequest entity) {
		ValidarDados.validarId(id, "Permissão");

		RolePermission rolePermission = RolePermissionConverter.toRolePermission(entity); 
		
		try {
			RolePermission rolePermissionCadastrado = rolePermissionRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("A Permissão não foi localizada %s", id)));
		
			rolePermissionCadastrado.setRole(rolePermission.getRole());
			rolePermissionCadastrado.setTarget(rolePermission.getTarget());
			rolePermissionCadastrado.setRead(rolePermission.getRead());
			rolePermissionCadastrado.setCreate(rolePermission.getCreate());
			rolePermissionCadastrado.setUpdate(rolePermission.getUpdate());
			rolePermissionCadastrado.setDelete(rolePermission.getDelete());
			
			rolePermissionCadastrado = rolePermissionRepository.saveAndFlush(rolePermissionCadastrado);
			
			return RolePermissionConverter.toRolePermissionResponse(rolePermissionCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar as permissões com id %s", id));
		}

	}

	

	@Override
	public RolePermissionResponse findById(Long id) {
		var rolePermission = rolePermissionRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Permissões não localizada %s", id)));

		var rolePermissionResponse = RolePermissionConverter.toRolePermissionResponse(rolePermission);

		return rolePermissionResponse;
	}

}
