package com.pdv.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;

	@NotBlank
	@Size(max = 15)
	private String txtNombreUsuario;
	
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
	
	@NotBlank
	@Size(max = 20)
	private String txtPassword;
	
	private Byte[] datosImagenUsuario;

	private PerfilDTO idPerfil;
	
	private Set<AlquilerAlojamientoDTO> idAlquileres;
	
	private Set<AlojamientoDTO> idAlojamiento;
	
	private Set<ValoracionAlojamientoDTO> idValoracionesAlojamientos;
	
}
