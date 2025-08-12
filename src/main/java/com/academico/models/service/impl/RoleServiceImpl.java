package com.academico.models.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Role;
import com.academico.models.repository.RoleRepository;
import com.academico.models.service.RoleService;
import com.academico.models.service.dto.converter.RoleConverter;
import com.academico.models.service.dto.request.RoleRequest;
import com.academico.models.service.dto.response.RoleResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.function.ValidarDados;
import com.academico.models.service.pagination.Page;
import com.academico.models.service.pagination.PageRequestConfig;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public RoleResponse save(RoleRequest entity) {

		var role = RoleConverter.toRole(entity);

		try {

			role = roleRepository.saveAndFlush(role);
			return RoleConverter.toRoleResponse(role);

		} catch (Exception e) {
			throw new NegocioException(FormatMessage.formatMessage(" Erro ao tentar incluir o role "));
		}

		
	}
	

	@Override
	public RoleResponse update(Long id, RoleRequest entity) {

		ValidarDados.validarId(id, "role");

		Role role = RoleConverter.toRole(entity);

		try {
			Role roleCadastrado = roleRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(FormatMessage.formatMessage("Role não localizada %s", id)));
		
			roleCadastrado.setNomeRole(role.getNomeRole());
			
			roleCadastrado = roleRepository.saveAndFlush(roleCadastrado);
			return RoleConverter.toRoleResponse(roleCadastrado);
		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar atualizar o usuário com id %s", id));
		}

	}

	@Override
	public RoleResponse updateField(Long id, Map<String, Object> fields) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void deleteById(Long id) {
		
		ValidarDados.validarId(id, "role");
	
		try {
			roleRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(FormatMessage.formatMessage("Registro solicitado para exclusão não foi encontrado com id = %s",id));
		} catch (Exception e) {
			throw new RuntimeException(
					FormatMessage.formatMessage("Erro ao tentar excluir o registro solicitado com o id =  %s", id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public RoleResponse findById(Long id) {
		
		ValidarDados.validarId(id, "role");
		
		
		var role = roleRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(FormatMessage.formatMessage("Role não localizada %s", id)));

		var roleResponse = RoleConverter.toRoleResponse(role);

		return roleResponse;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<RoleResponse> listar() {

		var listaRole = roleRepository.findAll();

		List<RoleResponse> lista = RoleConverter.toListaRoleResponse(listaRole);

		return lista;

	}

	@Override
	@Transactional(readOnly = true)
	public Page<RoleResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir, String props) {
		
		Pageable pageable = PageRequestConfig.gerarPagina(page, pageSize);

		String var = Role.getFieldNameUsingMap(props);

		List<Role> listaRoles = findRoles(key, pageable, var, dir);


		int total = 0;
		
		Page<RoleResponse> pagination = Page.createPagination(
				RoleConverter.toListaRoleResponse(listaRoles), 
                total, 
                pageable.getPageSize(), 
                pageable.getPageNumber() );
 
        return pagination;
	}

	

	private List<Role> findRoles(String key, Pageable pageable, String sortField, String dir) {
		return null;
	}



	
}
