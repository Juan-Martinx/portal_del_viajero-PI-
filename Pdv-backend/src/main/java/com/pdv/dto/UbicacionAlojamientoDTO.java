package com.pdv.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UbicacionAlojamientoDTO {

	private Long id;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{5}")
	private Integer codigoPostal;
	
	@NotBlank
	@Size(max = 60)
	private String ciudad;
	
	@Size(max = 60)
	private String provincia;
	
	@NotBlank
	private String lineaDireccion;
	
	private String longitud;
	
	private String latitud;
	
	private AlojamientoDTO idAlojamiento;

	
}
