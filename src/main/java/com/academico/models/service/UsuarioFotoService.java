package com.academico.models.service;

import com.academico.models.service.dto.request.ExcluirFotoRequest;
import com.academico.models.service.dto.request.FotoRequest;
import com.academico.models.service.dto.response.FotoResponse;

public interface UsuarioFotoService {

	public FotoResponse storeUsuarioFoto(FotoRequest fotoRequest);
	
	public FotoResponse deleteUsuarioFoto(ExcluirFotoRequest fotoRequest);
	
	public byte[] recuperarFoto(String nomeArquivo);
}

