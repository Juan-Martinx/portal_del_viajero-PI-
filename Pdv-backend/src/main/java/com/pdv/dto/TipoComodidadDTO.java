package com.pdv.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoComodidadDTO {

	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String codigoTipoComodidad;
	
	@NotBlank
	@Size(max = 50)
	private String txtNombre;
	
	private Set<ComodidadAlojamientoDTO> idComodidadAlojamientos;
}
