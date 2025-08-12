package com.academico.models.enums;

public enum PermissionsName {
	
	CREATE("CREATE"),
	UPDATE("UPDATE"),
	DELETE("DELETE"),
	READ("READ"),
	CREATE_OWNER("CREATE_OWNER"),
	UPDATE_OWNER("UPDATE_OWNER"),
	READ_OWNER("READ_OWNER");	
	
	private final String nomePermissions;

	private PermissionsName(String nomePermissions) {
		this.nomePermissions = nomePermissions;
	}

	public String getNomePermissions() {
		return nomePermissions;
	}
	
	

}
