package com.pdv.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
	
	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 20)
	private String txtNombreUsuario;
	
	private String txtDescripcion;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[0-9]{8}[A-Z]")
	private String txtDni;
	
	@NotNull
	@Pattern(regexp = "^[0-9]{9}")
	private Integer numTelefono;
	
	@NotNull
	@NotEmpty
	@Email
	private String txtEmail;
	
	@NotNull
	@NotEmpty
	@Size(max = 20)
	private String txtPassword;
	
	private Set<PerfilDTO> idPerfil;
	
}
