package com.pdv.service;

import org.springframework.stereotype.Service;

import com.pdv.dto.ImagenAlojamientoDTO;
import com.pdv.model.ImagenAlojamiento;

@Service
public class ImagenAlojamientoService {
	
	/**
	 * Método que sirve para pasar una entidad ImagenAlojamiento
	 * a su versión DTO.
	 * @param jpa
	 * @return
	 */
	public ImagenAlojamientoDTO toDto (ImagenAlojamiento jpa) {
		var dto = ImagenAlojamientoDTO.builder()
				.urlDatosImagen(jpa.getUrlDatosImagen())
				.numOrden(jpa.getNumOrden())
				.id(jpa.getId())
				.build();
		return dto;
	}
}
