package com.academico.models.service;

import java.io.InputStream;

import com.academico.models.service.dto.request.FotoRequest;
import com.academico.models.service.dto.response.FotoResponse;

public interface LocalFotoStorageService {

	public FotoResponse armazenar(FotoRequest fotoRequest);

	public boolean excluirFoto(String fileName);

	public InputStream recuperar(String nomeArquivo);

	public byte[] recuperarFoto(String nomeArquivo);

}