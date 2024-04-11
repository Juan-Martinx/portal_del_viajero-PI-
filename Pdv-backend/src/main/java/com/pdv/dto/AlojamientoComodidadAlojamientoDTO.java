package com.pdv.dto;

import lombok.Data;

@Data
public class AlojamientoComodidadAlojamientoDTO{
	
	private Long id;
	
	private ComodidadAlojamientoDTO idComodidadAlojamiento;

	private AlojamientoDTO idAlojamiento;
}
