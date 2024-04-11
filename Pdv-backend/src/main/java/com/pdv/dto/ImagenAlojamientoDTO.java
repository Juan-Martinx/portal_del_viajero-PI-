package com.pdv.dto;

import com.pdv.model.Alojamiento;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ImagenAlojamientoDTO{

	private Long id;
	
	@NotNull
	private Byte[] datosImagen;
	
	@NotNull
	private Integer numOrden;
	
	private AlojamientoDTO idAlojamiento;
	
}