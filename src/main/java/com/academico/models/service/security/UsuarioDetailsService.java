package com.academico.models.service.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.academico.models.model.Usuario;
import com.academico.models.model.UsuarioRolePermission;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.repository.UsuarioRolePermissionRepository;
import com.academico.models.service.exception.UserLockedException;
import com.academico.models.service.function.FormatMessage;


@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioRolePermissionRepository usuarioRolePermissionRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findUsuarioByEmail(email);
		
		if ( !usuario.isPresent() ) {
			throw new UsernameNotFoundException(" Usuário não cadastrado! "+email);
		}
		
		Usuario usuarioLogado = usuario.get();
		
		if (usuarioLogado.getEmail().equals(email) && usuarioLogado.isAtivo() == false ) {
			throw new UserLockedException(FormatMessage.formatMessage("Usuário está bloqueado no sistema"));
		}
		
		UsuarioSistema usuarioSistema = new UsuarioSistema(usuarioLogado, getPermissoes(usuarioLogado));
		
		return usuarioSistema;
		
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		
		Set<UsuarioRolePermission> urpList = usuarioRolePermissionRepository.findByUsuario(usuario);

	    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	    
	    for (UsuarioRolePermission urp : urpList ) {
	    	authorities.add(new SimpleGrantedAuthority("ROLE_"+urp.getRole().getNomeRole()));
	    }
		
        for (UsuarioRolePermission urp : urpList ) {
	    	authorities.add(new SimpleGrantedAuthority(urp.getPermission().getNomePermission().getNomePermissions()));
	    }
	
	    return authorities;
	}
}
