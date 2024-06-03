package com.pdv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.ValoracionAlojamientoDTO;
import com.pdv.service.ValoracionAlojamientoService;

import lombok.RequiredArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.VALORACION_ALOJAMIENTO)
@RequiredArgsConstructor
public class ValoracionAlojamientoController {
	
	private final ValoracionAlojamientoService valoracionAlojamientoService;
	
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> crearValoracion(@RequestBody ValoracionAlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.valoracionAlojamientoService.crearValoracion(dto, autenticacion));
	}
	
}
