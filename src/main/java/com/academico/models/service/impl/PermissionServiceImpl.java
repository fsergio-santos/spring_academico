package com.academico.models.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Permission;
import com.academico.models.repository.PermissionRepository;
import com.academico.models.service.PermissionService;
import com.academico.models.service.dto.converter.PermissionConverter;
import com.academico.models.service.dto.request.PermissionRequest;
import com.academico.models.service.dto.response.PermissionResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;


	@Override
	public PermissionResponse save(PermissionRequest entity) {

		var permission = PermissionConverter.toPermission(entity);

		try {

			permission = permissionRepository.saveAndFlush(permission);
			return PermissionConverter.toPermissionResponse(permission);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(" Erro ao tentar incluir o permission "));
		}

		
	}
	

	@Override
	public PermissionResponse update(Long id, PermissionRequest entity) {

		ValidarDados.validarId(id, "permission");

		Permission permission = PermissionConverter.toPermission(entity);

		try {
			Permission permissionCadastrado = permissionRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("Permission não localizada %s", id)));
		
			permissionCadastrado.setNomePermission(permission.getNomePermission());
			
			permissionCadastrado = permissionRepository.saveAndFlush(permissionCadastrado);
			return PermissionConverter.toPermissionResponse(permissionCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", id));
		}

	}

	@Override
	public PermissionResponse updateField(Long id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void deleteById(Long id) {
		
		ValidarDados.validarId(id, "permission");
	
		try {
			permissionRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage("Registro solicitado para exclusão não foi encontrado com id = %s",id));
		} catch (Exception e) {
			throw new RuntimeException(
					FormatMessage.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PermissionResponse findById(Long id) {
		
		ValidarDados.validarId(id, "permission");
		
		
		var permission = permissionRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Permission não localizada %s", id)));

		var permissionResponse = PermissionConverter.toPermissionResponse(permission);

		return permissionResponse;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<PermissionResponse> listar() {

		var listaPermission = permissionRepository.findAll();

		List<PermissionResponse> lista = PermissionConverter.toListaPermissionResponse(listaPermission);

		return lista;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<PermissionResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {
        return null;
	}

	
	
}
