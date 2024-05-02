package com.pdv.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlojamientoDTO {
	
	private Long id;
	
	@NotBlank
	@Size(min = 6, max = 50)
	private String txtNombre;
	
	@NotBlank
	private String txtDescripcion;
	
	@NotNull
	private Integer numPlazaMin;
	
	@NotNull
	private Integer numPlazaMax;
	
	@NotNull
	private Double numPrecioNoche;
	
	private UsuarioDTO idUsuario;
	
	private Set<ValoracionAlojamientoDTO> idValoracionesAlojamiento;
	
	private Set<AlquilerAlojamientoDTO> idAlquileresAlojamiento;
	
	private Set<ImagenAlojamientoDTO> idImagenesAlojamiento;
	
	private UbicacionAlojamientoDTO idUbicacion;
	
	private Set<AlojamientoComodidadAlojamientoDTO> idAlojamientoComodidades;
}
