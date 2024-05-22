package com.pdv.dto;

import java.util.Set;

import com.pdv.enums.CodPerfiles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PerfilDTO {
	
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private CodPerfiles codPerfil;

	@NotBlank
	private String txtPerfil;
	
	private Set<UsuarioDTO> idUsuarios;
	
}
