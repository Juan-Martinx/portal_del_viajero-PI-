package com.pdv.dto;

import java.util.Set;

import com.pdv.model.TipoComodidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
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
	
	private TipoComodidadDTO idTipoComodidad;

	private Set<AlojamientoComodidadAlojamientoDTO> idAlojamientoComodidades;
}
