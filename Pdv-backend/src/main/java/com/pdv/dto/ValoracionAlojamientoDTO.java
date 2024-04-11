package com.pdv.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValoracionAlojamientoDTO {

	private Long id;
	
	@NotBlank
	private String txtMensaje;
	
	@NotBlank
	@Size(max = 50)
	private String txtAsunto;
	
	@NotBlank
	@Size(max = 60)
	private String provincia;
	
	@NotBlank
	private Integer puntuacion;
	
	private UsuarioDTO idUsuarioValorador;
	
	private AlojamientoDTO idAlojamiento;
	
}
