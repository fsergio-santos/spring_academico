package com.academico.models.service.dto.converter;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Usuario;
import com.academico.models.repository.projection.UsuarioCidadeProjection;
import com.academico.models.service.dto.request.RegistrarRequest;
import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.models.service.dto.response.LoginResponse;
import com.academico.models.service.dto.response.UsuarioResponse;
import com.academico.models.service.function.EntityConverter;

public class UsuarioConverter {

	public static Usuario toUsuario(UsuarioRequest usuarioRequest) {
		return EntityConverter.convert(usuarioRequest, Usuario.class);
	}
	
	
	public static UsuarioResponse toUsuarioResponse(Usuario usuario) {
		
		String  senha = null;
		String  confirmSenha= null;
		
		Long    idAluno  = null;
		String  codAluno = null;
	    String  nomeAluno = null;
	    Integer idadeAluno = null;
	    
	    
	    if (usuario.getAluno() != null) {
	    	idAluno  = usuario.getAluno().getIdAluno();
	        codAluno = usuario.getAluno().getCodAluno();
	        nomeAluno = usuario.getAluno().getNomeAluno();
	        idadeAluno = usuario.getAluno().getIdade();
	    }
	    
	    Long idProfessor = null;
	    String codProfessor = null;
	    String nomeProfessor = null;
	    
	    if (usuario.getProfessor() != null) {
	    	idProfessor  = usuario.getProfessor().getIdProfessor();
	        codProfessor = usuario.getProfessor().getCodProfessor();
	        nomeProfessor = usuario.getProfessor().getNomeProfessor();
	    }
		


		return new UsuarioResponse(
     			    usuario.getIdUsuario(),
				    usuario.getCodUsuario(),
				    usuario.getNomeUsuario(),
				    usuario.getEmail(),
				    senha,
				    usuario.getTipo(), 
				    confirmSenha,
				    usuario.getCidade().getIdCidade(),
				    idAluno,
					codAluno,
					nomeAluno,
					idadeAluno,
					idProfessor,
					codProfessor,
					nomeProfessor,
					usuario.getFoto(),
					usuario.isAtivo()
				);
	}
	
	public static UsuarioResponse toListaUsuarioResponse(Usuario usuario) {
		return new UsuarioResponse(
				    usuario.getIdUsuario(),
				    usuario.getCodUsuario(),
				    usuario.getNomeUsuario(),
				    usuario.getEmail(),
				    usuario.getTipo(),
				    usuario.getCidade().getNomeCidade(),
				    usuario.getFoto(),
				    usuario.isAtivo()
				);
	}
	
	
	
	public static List<UsuarioResponse> toListaUsuarioResponse(List<Usuario> listaUsuario){
           
		   List<UsuarioResponse> listaUsuarioResponse = new ArrayList<UsuarioResponse>();
		
		   for (Usuario usuario :listaUsuario ) {
			   var usuarioResponse = toListaUsuarioResponse(usuario);
			   listaUsuarioResponse.add(usuarioResponse);
		   }
		   return listaUsuarioResponse;
		   
		
	}


	public static List<UsuarioResponse> projectionToListaUsuarioResponse(List<UsuarioCidadeProjection> projections) {
		return projections.stream()
	            .map(projection -> new UsuarioResponse(
	                projection.getIdUsuario(),
	                projection.getCodUsuario(),
	                projection.getNomeUsuario(),
	                projection.getEmail(),
	                projection.getTipo(),
	                projection.getNomeCidade(),
	                projection.getFoto(),
	                projection.getAtivo()
	            ))
	            .collect(Collectors.toList());
	}
	
	
	public static LoginResponse usuarioToLoginResponse(Usuario usuario, String access_token, String refresh_token, List<String> roles, List<String> permissions ) {
		
//		List<List<Long>> usuariosSimplificados = usuario.getUsuarios().stream()
//			    .map(urp -> List.of(
//			    		Long.valueOf(urp.getId().getIdUsuario()), 
//			            Long.valueOf(urp.getId().getIdRole()), 
//			            Long.valueOf(urp.getId().getIdPermission())
//			    )).collect(Collectors.toList());
		
		return new LoginResponse (
				  usuario.getIdUsuario(),
				  usuario.getNomeUsuario(),
                  usuario.getEmail(),
                  usuario.getFoto(),
            	  access_token,
				  refresh_token, 
				  roles,
				  permissions
				);
	}
	

	public static Usuario toUsuario(RegistrarRequest registrarRequest) {
		return new Usuario(
				registrarRequest.getNomeUsuario(),
				registrarRequest.getEmail(),
				registrarRequest.getSenha()
				);
	}
	

	
	
	
	
}
