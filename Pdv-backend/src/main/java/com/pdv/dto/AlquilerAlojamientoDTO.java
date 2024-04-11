package com.pdv.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlquilerAlojamientoDTO {
	
	
	private Long id;
	
	@NotBlank
	private LocalDate fechaInicioAlquiler;
	
	@NotBlank
	private LocalDate fechaFinAlquiler;
	
	@NotBlank
	private Double precioTotalAlquiler;
	
	@NotBlank
	private Integer numPlazasReservadas;
	
	private UsuarioDTO idUsuarioCliente;
	
	private AlojamientoDTO idAlojamiento;
	
}
