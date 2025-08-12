package com.academico.models.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academico.models.model.Usuario;
import com.academico.models.repository.impl.UsuarioRepositoryCustom;
import com.academico.models.repository.projection.UsuarioCidadeProjection;



public interface UsuarioRepository extends JpaRepository<Usuario,Long>, UsuarioRepositoryCustom {
	
	    
	    @Query(value = "SELECT COUNT(*) FROM Usuario ", nativeQuery = true)
        int countUsuarios();

	    
	    @Query("SELECT new com.academico.models.model.Usuario( "
	    		+ "u.idUsuario, "
	    		+ "u.codUsuario,"
	    		+ "u.nomeUsuario, "
	    		+ "u.email, "
	    		+ "u.senha, "
	    		+ "u.foto, "
	    		+ "u.tipo, "
	    		+ "u.otp, "
	    		+ "u.otpExpiry, "
	    		+ "u.ativo ) "
	    		+ "FROM Usuario u "
	    		+ "WHERE u.idUsuario =:id")
		Optional<Usuario> findUsuarioById(@Param("id") Long id);
	    
	    
	    @Query("SELECT u FROM Usuario u "
	    		+ "WHERE u.email =:email")
		Optional<Usuario> findUsuarioByEmail(@Param("email") String email);
	   
	    @Query("SELECT "
	    		+ "u.idUsuario AS idUsuario, "
	    		+ "u.codUsuario AS codUsuario, "
	    		+ "u.nomeUsuario AS nomeUsuario, "
	    		+ "u.email AS email, "
	    		+ "u.tipo AS tipo, "
	    		+ "u.foto AS foto, "
	    		+ "u.ativo AS ativo, "
	    		+" c.nomeCidade AS nomeCidade "
	    		+ "FROM Usuario u "
	    		+ "LEFT JOIN u.cidade c ")
	    List<UsuarioCidadeProjection> listagemUsuario();
	    
	    
	    @Query(value="SELECT u FROM Usuario u "
		    		+"LEFT JOIN FETCH u.cidade c "
		    		+"LEFT JOIN FETCH u.aluno a "
		    		+"LEFT JOIN FETCH u.professor p "
		    		+"WHERE u.idUsuario =:idUsuario ")
	    Optional<Usuario> findByidUsuario(@Param("idUsuario") Long idUsuario);
	    
	    
	    @Query(value="SELECT u.tipo, COUNT(u) FROM Usuario u GROUP BY u.tipo ")
	    List<Object[]> totalsByTipoUsuario();
	    
	    
	    @Modifying
		@Query("UPDATE Usuario u "
				+ "SET u.ativo =:ativo "
				+ "WHERE u.id=:id ")
		void activate(@Param("id") Long id, @Param("ativo") boolean ativo);
	    
	    @Modifying
        @Query("UPDATE Usuario u "
        		+ "SET u.otp=:otpValue, "
        		+ "u.otpExpiry =:timeStamp "
        		+ "WHERE u.id=:id ")
		void updateOpt(@Param("otpValue") String otpValue, @Param("timeStamp") LocalDateTime timeStamp, @Param("id") Long id);

	    
	    @Modifying
        @Query("UPDATE Usuario u "
        		+ "SET u.senha=:senha "
        		+ "WHERE u.id=:id ")
		void updatePassword(@Param("id") Long id, @Param("senha") String senha);
	    
	    
}
