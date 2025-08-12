package com.academico.models.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Permission;
import com.academico.models.model.Role;
import com.academico.models.model.Usuario;
import com.academico.models.model.UsuarioRolePermission;
import com.academico.models.model.UsuarioRolePermissionId;
import com.academico.models.repository.PermissionRepository;
import com.academico.models.repository.RoleRepository;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.repository.UsuarioRolePermissionRepository;
import com.academico.models.service.UsuarioRolePermissionService;
import com.academico.models.service.dto.converter.UsuarioRolePermissionConverter;
import com.academico.models.service.dto.request.UsuarioRolePermissionRequest;
import com.academico.models.service.dto.response.UsuarioRolePermissionResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;
import com.academico.models.service.pagination.Page;

@Service
@Transactional
public class UsuarioRolePermissionServiceImpl implements UsuarioRolePermissionService {

	@Autowired
	private UsuarioRolePermissionRepository usuarioRolePermissionRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioRolePermissionResponse> listar() {
		List<UsuarioRolePermission> lista = usuarioRolePermissionRepository.findAll();
		return UsuarioRolePermissionConverter.toListaUsuarioRolePermissionResponse(lista);
	}

	@Override
	public void save(UsuarioRolePermissionRequest entity) {
		
		UsuarioRolePermissionId usuarioRolePermissionId = UsuarioRolePermissionConverter
				.toUsuarioRolePermission(entity);
	
		try {
		
			Usuario usuario = usuarioRepository.findById(entity.getIdUsuario())
					.orElseThrow(() -> new EntityNotFoundException(FormatMessage
							.formatMessage("Usuario não localizada %s", String.valueOf(entity.getIdUsuario()))));

			Role role = roleRepository.findById(entity.getIdRole()).orElseThrow(() -> new EntityNotFoundException(
					FormatMessage.formatMessage("Role não localizada %s", entity.getIdRole())));

			Permission permission = permissionRepository.findById(entity.getIdPermission())
					.orElseThrow(() -> new EntityNotFoundException(
							FormatMessage.formatMessage("Permission não localizada %s", entity.getIdPermission())));

			UsuarioRolePermission usuarioRolePermission = new UsuarioRolePermission(usuarioRolePermissionId, usuario, role, permission);

			usuarioRolePermission = usuarioRolePermissionRepository.saveAndFlush(usuarioRolePermission);
			
			//return UsuarioRolePermissionConverter.toUsuarioRolePermissionResponse(usuarioRolePermission);

		} catch (Exception e) {
			throw new NegocioException(
					FormatMessage.formatMessage("Erro ao tentar salvar as permissiões do usuário "+ e.getMessage()));
		}

		
	}

	@Override
	public void update(Long id, List<UsuarioRolePermissionRequest> entity) {
		
		List<UsuarioRolePermission> novasPermissao = UsuarioRolePermissionConverter.toListUsuarioRolePermission(entity);
				
		List<UsuarioRolePermission> direitosExistentes = usuarioRolePermissionRepository.findUsuarioRolePermissionById(id);

		List<UsuarioRolePermission> direitosParaAdicionar = novasPermissao.stream()
				 .filter( novoDireito -> direitosExistentes.stream()
				 		     .noneMatch(direitoExistente -> 
				             direitoExistente.getUsuario().getIdUsuario().equals(novoDireito.getUsuario().getIdUsuario()) &&
				             direitoExistente.getRole().getIdRole().equals(novoDireito.getRole().getIdRole()) &&
				             direitoExistente.getPermission().getIdPermission().equals(novoDireito.getPermission().getIdPermission())
						 )
				).collect(Collectors.toList());


		List<UsuarioRolePermission> direitosParaRemover = direitosExistentes.stream()
		    .filter(direitoExistente -> novasPermissao.stream()
		        .noneMatch(novoDireito -> 
		         direitoExistente.getUsuario().getIdUsuario().equals(novoDireito.getUsuario().getIdUsuario()) &&
	             direitoExistente.getRole().getIdRole().equals(novoDireito.getRole().getIdRole()) &&
	             direitoExistente.getPermission().getIdPermission().equals(novoDireito.getPermission().getIdPermission())
		        )
		    )
		    .collect(Collectors.toList());

		usuarioRolePermissionRepository.deleteAll(direitosParaRemover);
		
		usuarioRolePermissionRepository.saveAll(direitosParaAdicionar);
	  
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioRolePermissionResponse> listaPaginada(String key, Integer page, Integer pageSize, String dir,
			String props) {
		return null;
	}

	@Override
	public List<UsuarioRolePermissionResponse> loadRolesAndPermissionById(Long idUsuario) {
		List<UsuarioRolePermission> lista = usuarioRolePermissionRepository.findUsuarioRolePermissionById(idUsuario);
		
		return UsuarioRolePermissionConverter.toListaUsuarioRolePermissionResponse(lista);
	}

}

