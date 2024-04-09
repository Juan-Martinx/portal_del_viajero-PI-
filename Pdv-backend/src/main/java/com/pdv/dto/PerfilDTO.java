package com.pdv.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilDTO {
	
	@NotNull
	//@UniqueConstraint
	@Max(value = 50)
	private String codPerfil;

}
