package com.academico.models.enums;

public enum OperationsName {

	INSERT("INSERT"),
	UPDATE("UPDATE"),
	DELETE("DELETE");
	
    private final String nomeOperacao;
	
	OperationsName(String nomeOperacao) {
	   this.nomeOperacao = nomeOperacao;	
	}

	public String getNome() {
		return nomeOperacao;
	}
	
}
