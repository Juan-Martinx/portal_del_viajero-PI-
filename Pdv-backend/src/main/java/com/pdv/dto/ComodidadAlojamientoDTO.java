package com.pdv.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class ComodidadAlojamientoDTO {

	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String codigoComodidad;
	
	@NotBlank
	@Size(max = 50)
	private String txtNombre;
	
	@NotBlank
	private String txtDescripcion;
	
	private String iconoComodidad;

	private TipoComodidadDTO idTipoComodidad;

	private Set<AlojamientoComodidadAlojamientoDTO> idAlojamientoComodidades;
}
