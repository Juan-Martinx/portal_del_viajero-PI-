package com.pdv.dto;

import java.util.Set;

import com.pdv.model.AlojamientoComodidadAlojamiento;
import com.pdv.model.AlquilerAlojamiento;
import com.pdv.model.ImagenAlojamiento;
import com.pdv.model.UbicacionAlojamiento;
import com.pdv.model.Usuario;
import com.pdv.model.ValoracionAlojamiento;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlojamientoDTO {
	
	private Long id;
	
	@NotNull
	@NotEmpty
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
	
	private Usuario gestorAlojamiento;
	
	private Set<ValoracionAlojamiento> valoracionesAlojamiento;
	
	private Set<AlquilerAlojamiento> alquileresAlojamiento;
	
	private Set<ImagenAlojamiento> imagenesAlojamiento;
	
	private UbicacionAlojamiento ubicacion;
	
	private Set<AlojamientoComodidadAlojamiento> alojamientoComodidades;
}
