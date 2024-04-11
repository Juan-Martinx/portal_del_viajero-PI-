package com.pdv.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlquilerAlojamientoDTO {
	
	private Long id;
	
	@NotBlank
	@FutureOrPresent
	private LocalDate fechaInicioAlquiler;
	
	@NotBlank
	@FutureOrPresent
	private LocalDate fechaFinAlquiler;
	
	@NotBlank
	private Double precioTotalAlquiler;
	
	@NotBlank
	private Integer numPlazasReservadas;
	
	private UsuarioDTO idUsuarioCliente;
	
	private AlojamientoDTO idAlojamiento;
	
}
