package com.pdv.dto;

import java.util.Set;

import com.pdv.model.AlquilerAlojamiento;
import com.pdv.model.ImagenAlojamiento;
import com.pdv.model.UbicacionAlojamiento;
import com.pdv.model.ValoracionAlojamiento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlojamientoDTO {
	
	private Long id;
	
	@NotBlank
	private String txtNombre;
	
	private String txtDescripcion;
	
	@NotNull
	private Integer numPlazaMin;
	
	@NotNull
	private Integer numPlazaMax;
	
	@NotNull
	private Double numPrecioPlaza;
	
	@NotNull
	private Integer numCamas;
	
	@NotNull
	private Integer numbanyos;
	
	private UsuarioDTO idGestorAlojamiento;
	
	private Set<ValoracionAlojamientoDTO> idValoracionesAlojamiento;
	
	private Set<AlquilerAlojamientoDTO> idAlquileresAlojamiento;
	
	private Set<ImagenAlojamientoDTO> idImagenesAlojamiento;
	
	private UbicacionAlojamientoDTO idUbicacion;
	
	private Set<AlojamientoComodidadAlojamientoDTO> idAlojamientoComodidades;
}
