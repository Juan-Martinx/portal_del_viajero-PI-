package com.pdv.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ImagenAlojamientoDTO{

	private Long id;
	
	@NotNull
	private String urlDatosImagen;
	
	@NotNull
	private Integer numOrden;
	
	private AlojamientoDTO idAlojamiento;
	
}