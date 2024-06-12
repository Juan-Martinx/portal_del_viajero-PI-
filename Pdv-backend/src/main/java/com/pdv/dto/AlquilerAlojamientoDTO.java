package com.pdv.dto;

import java.time.LocalDate;

import com.pdv.model.Alojamiento;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
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
	
	private AlojamientoDTO alojamiento;
}
