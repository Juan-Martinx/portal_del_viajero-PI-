package com.pdv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.AlojamientoDTO;
import com.pdv.dto.AlquilerAlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.service.AlquilerAlojamientoService;

import lombok.RequiredArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.ALQUILER_ALOJAMIENTO)
@RequiredArgsConstructor
public class AlquilerAlojamientoController {
	
	private final AlquilerAlojamientoService alquilerAlojamientoService;
	
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> realizarReserva(@RequestBody AlquilerAlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alquilerAlojamientoService.realizarReserva(dto, autenticacion));
	}
}
