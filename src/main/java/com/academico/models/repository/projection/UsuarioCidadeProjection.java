package com.academico.models.repository.projection;

public interface UsuarioCidadeProjection {
	
	Long    getIdUsuario();
    String  getCodUsuario();
    String  getNomeUsuario();
    String  getEmail();
    Integer getTipo();
    String  getNomeCidade();
    String  getFoto();
    Boolean getAtivo();

}
