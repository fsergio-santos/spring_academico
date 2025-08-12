package com.academico.models.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.academico.models.service.LocalFotoStorageService;
import com.academico.models.service.dto.request.FotoRequest;
import com.academico.models.service.dto.response.FotoResponse;
import com.academico.models.service.exception.FileStorageException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;



@Service
@Transactional
public class LocalFotosStorageServiceImpl implements LocalFotoStorageService  {
	
	private static final String DIRETORIO_FOTOS = "/arquivos/nds/files/catalogo";
	
	private static final long MAX_FILE_SIZE = 209715200;
	
	private Path diretorioFotos;

    public LocalFotosStorageServiceImpl() {
		try {
            diretorioFotos = Paths.get(DIRETORIO_FOTOS).toAbsolutePath().normalize();  
			Files.createDirectories(diretorioFotos);
		} catch (IOException e) {
			throw new FileStorageException("Não foi possível criar diretórios de fotos ", e);
		}
	}

  
	@SuppressWarnings("unlikely-arg-type")
	public FotoResponse armazenar(FotoRequest fotoRequest) {
    	
		FotoResponse foto = new FotoResponse();
		
		String nomeFoto = null;
		
		if (fotoRequest.getFoto().isEmpty()) {
			throw new IllegalStateException("Não pode enviar arquivo de imagem vázio  [ "+ fotoRequest.getFoto().getSize()+"]");
		}
		
		if (fotoRequest.getFoto().getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Arquivo excede o tamanho máximo permitido.");
        }
		
	    if (!fotoRequest.getFoto().getContentType().startsWith("image/")) {
			 throw new IllegalStateException("Apenas arquivos de imagem são permitidos");
	    }
		 
		 
		if (Arrays.asList(ContentType.IMAGE_JPEG, 
				           ContentType.IMAGE_PNG).contains(fotoRequest.getFoto().getContentType())) {
			throw new IllegalStateException("Tipo de arquivo de imagem não é válido");
		}
		
        nomeFoto = gerarNomeArquivo(fotoRequest.getFoto().getOriginalFilename());
		    
		try {
			Path arquivoPath = getArquivoPath(nomeFoto);
			
			FileCopyUtils.copy(fotoRequest.getFoto().getInputStream(), Files.newOutputStream(arquivoPath));
			Thumbnails.of(arquivoPath.toString()).size(50, 78).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
			foto.setContentType(fotoRequest.getFoto().getContentType());
			foto.setNomeArquivo(nomeFoto);
			return foto;
		} catch (IOException e) {
			throw new FileStorageException("Erro na gravação da foto ", e);
		}
	}

	
   public boolean excluirFoto(String fileName) {
			    
		String thumbnail = "thumbnail."+fileName;
        if (!fileName.isEmpty()) { 
			try {
				
				Path arquivoThumbinailPath = getArquivoPath(thumbnail);
				Files.deleteIfExists(arquivoThumbinailPath);
				
				Path arquivoPath = getArquivoPath(fileName);
				Files.deleteIfExists(arquivoPath);
				
				return true;
			} catch (IOException e) {
				throw new FileStorageException("Erro na exclusão da foto", e);
			}
		}
        return false;
	}

	
	public InputStream recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			return Files.newInputStream(arquivoPath);
		} catch (IOException e) {
			throw new FileStorageException("Erro na recuperação da foto ", e);
		}
	}

	public byte[] recuperarFoto(String nomeArquivo) {
		try {
			return Files.readAllBytes(getArquivoPath(nomeArquivo));
		} catch (IOException e) {
			throw new FileStorageException("Erro na leitura da foto ", e);
		}
	}

	private String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Paths.get(nomeArquivo));
	}
	
	
	

}
