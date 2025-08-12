package com.academico.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.models.repository.UsuarioRepository;
import com.academico.models.service.LocalFotoStorageService;
import com.academico.models.service.UsuarioFotoService;

import com.academico.models.service.dto.request.ExcluirFotoRequest;
import com.academico.models.service.dto.request.FotoRequest;
import com.academico.models.service.dto.response.FotoResponse;
import com.academico.models.service.exception.EntityNotFoundException;
import com.academico.models.service.imagem.ImagemValida;

@Service
public class UsuarioFotoServiceImpl implements UsuarioFotoService {
	
	@Autowired
	private LocalFotoStorageService localFotoStorageService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public FotoResponse storeUsuarioFoto(FotoRequest fotoRequest) {
		
		ImagemValida.validarImagem(fotoRequest.getFoto());
		
		if ( Long.valueOf(fotoRequest.getId()) != 0L ) {
            var usuario = usuarioRepository.findById(Long.valueOf(fotoRequest.getId()))
            		                       .orElseThrow(()-> new EntityNotFoundException(
            		   							String.format("Usuario não localizada %s",
            		   									fotoRequest.getId())));
            localFotoStorageService.excluirFoto(usuario.getFoto());
		} 
		
		var fotoResponse = localFotoStorageService.armazenar(fotoRequest);
		
		
		return fotoResponse;
	}


	@Override
	public FotoResponse deleteUsuarioFoto(ExcluirFotoRequest fotoRequest) {
		
		boolean excluido = false; 
		
		if ( Long.valueOf(fotoRequest.getId()) != 0L ) {
            var usuario = usuarioRepository.findById(Long.valueOf(fotoRequest.getId()))
            		                       .orElseThrow(()-> new EntityNotFoundException(
            		   							String.format("Usuario não localizada %s",
            		   									fotoRequest.getId())));
            excluido = localFotoStorageService.excluirFoto(usuario.getFoto());
     
		} else {
			excluido = localFotoStorageService.excluirFoto(fotoRequest.getNomeArquivo());
		}
		
		
		if (excluido) {
			FotoResponse fotoResponse = new FotoResponse();
			fotoResponse.setId(0L);
			fotoResponse.setContentType("");
			fotoResponse.setNomeArquivo("");
			return fotoResponse;

		}
		
		return null;
		
	}

	@Override
	public byte[] recuperarFoto(String nomeArquivo) {
		return localFotoStorageService.recuperarFoto(nomeArquivo);
	}

}
