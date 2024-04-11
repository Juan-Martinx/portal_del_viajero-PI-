package com.pdv.dto;

import com.pdv.model.Alojamiento;
import com.pdv.model.ComodidadAlojamiento;

import lombok.Data;

@Data
public class AlojamientoComodidadAlojamientoDTO {
	
	private Long id;

	private ComodidadAlojamiento idComodidadAlojamiento;

	private Alojamiento alojamiento;
}
