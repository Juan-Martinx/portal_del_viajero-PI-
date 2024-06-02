package com.pdv.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlquilerAlojamientoDTO {
	
	private Long id;
	
	@NotNull
	@FutureOrPresent
	private LocalDate fechaInicioAlquiler;
	
	@NotNull
	@FutureOrPresent
	private LocalDate fechaFinAlquiler;
	
	@NotNull
	private Double precioTotalAlquiler;
	
	@NotNull
	private Integer numPlazasReservadas;
	
	private UsuarioDTO idUsuario;
	
	private Long idAlojamiento;
	
}
