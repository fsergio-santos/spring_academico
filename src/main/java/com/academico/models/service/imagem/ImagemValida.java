package com.academico.models.service.imagem;

import java.util.Arrays;
import java.util.Objects;

import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;

public class ImagemValida {
	
	@SuppressWarnings("unlikely-arg-type")
	public static void validarImagem(MultipartFile file) {
		if (Objects.isNull(file)) {
			throw new NullPointerException("O arquivo não pode ser nulo");
		}
		if (file.isEmpty()) {
			throw new IllegalStateException("Não pode enviar arquivo de imagem vázio  [ "+ file.getSize()+"]");
		}
		
		if (Arrays.asList(ContentType.IMAGE_JPEG, 
				           ContentType.IMAGE_PNG, 
				           ContentType.IMAGE_GIF).contains(file.getContentType())) {
			throw new IllegalStateException("Tipo de arquivo de imagem não é válido");
		}
	}

}
