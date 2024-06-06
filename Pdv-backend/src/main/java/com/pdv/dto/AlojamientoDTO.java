package com.pdv.dto;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
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
	
	private Double valoracionPromedio;
	
	private Integer numValoraciones;
		
	private UsuarioDTO idUsuario;
	
	private List<Long> idComodidades;
	
	private Set<ValoracionAlojamientoDTO> idValoracionesAlojamiento;
	
	private Set<AlquilerAlojamientoDTO> idAlquileresAlojamiento;
	
	private List<ImagenAlojamientoDTO> idImagenesAlojamiento;
	
	private UbicacionAlojamientoDTO idUbicacion;
	
	private Set<AlojamientoComodidadAlojamientoDTO> idAlojamientoComodidades;
}
