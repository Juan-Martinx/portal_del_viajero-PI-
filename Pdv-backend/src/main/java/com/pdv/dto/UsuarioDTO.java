package com.pdv.dto;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private Long id;

	@NotBlank
	@Size(max = 15)
	private String username;
	
	@NotBlank
	@Size(max = 20)
	private String password;
	
	private String txtDescripcion;

	@NotBlank
	@Pattern(regexp = "^[0-9]{8}[A-Z]")
	private String txtDni;
	
	@NotNull
	@Pattern(regexp = "^[0-9]{9}")
	private Integer numTelefono;
	
	@NotBlank
	@Email
	private String txtEmail;
	
	private String urlImagenUsuario;

	private List<String> perfiles;
	
	private Set<AlquilerAlojamientoDTO> idAlquileres;
	
	private Set<AlojamientoDTO> idAlojamiento;
	
	private Set<ValoracionAlojamientoDTO> idValoracionesAlojamientos;
	
}
