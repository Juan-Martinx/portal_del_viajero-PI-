package com.pdv.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String txtNombreUsuario;
	
	private String txtDescripcion;

	@NotBlank
	@Pattern(regexp = "^[0-9]{8}[A-Z]")
	private String txtDni;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{9}")
	private Integer numTelefono;
	
	@NotBlank
	@Email
	private String txtEmail;
	
	@NotBlank
	@Size(max = 20)
	private String txtPassword;
	
	private PerfilDTO idPerfil;
	
	private Set<AlquilerAlojamientoDTO> alquilerAlojamientoDTO;
	
	private Set<AlojamientoDTO> alojamientosDTO;
	
	private Set<ValoracionAlojamientoDTO> valoracionesAlojamientosDTO;

	
}
