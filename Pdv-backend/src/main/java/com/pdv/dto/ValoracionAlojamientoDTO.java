package com.pdv.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ValoracionAlojamientoDTO {

	private Long id;
	
	@NotBlank
	private String txtMensaje;
	
	@NotBlank
	@Size(max = 50)
	private String txtAsunto;
	
	@NotNull
	private Integer puntuacion;
	
	private UsuarioDTO idUsuario;
	
	private AlojamientoDTO idAlojamiento;
	
}
