package com.pdv.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerfilDTO {
	
	private Long id;
	
	@NotNull
	@Size(max = 50)
	private String codPerfil;

	@NotNull
	private String txtPerfil;
	
}
