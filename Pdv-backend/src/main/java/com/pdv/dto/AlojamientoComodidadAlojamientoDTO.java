package com.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlojamientoComodidadAlojamientoDTO{
	
	private Long id;
	
	private ComodidadAlojamientoDTO idComodidadAlojamiento;

	private AlojamientoDTO idAlojamiento;
}
