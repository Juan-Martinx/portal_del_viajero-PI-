package com.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para realizar la reserva de 
	 * un alojamiento.
	 * @param dto
	 * @param autenticacion
	 * @return
	 */
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> realizarReserva(@RequestBody AlquilerAlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alquilerAlojamientoService.realizarReserva(dto, autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar las reservas del usuario
	 * logueado dentro de la aplicación.
	 * @param autenticacion
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<AlquilerAlojamientoDTO>> buscarReservas(Authentication autenticacion){
		return ResponseEntity.ok().body(this.alquilerAlojamientoService.buscarReservasUsuario(autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar las reservas que han realizado
	 * otros usuarios sobre tus propiedades.
	 * @param autenticacion
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@GetMapping("/gestor")
	public ResponseEntity<List<AlquilerAlojamientoDTO>> buscarReservasGestor(Authentication autenticacion){
		return ResponseEntity.ok().body(this.alquilerAlojamientoService.buscarReservasGestor(autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar las reservas que han realizado
	 * otros usuarios por su username.
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@GetMapping("/{username}")
	public ResponseEntity<List<AlquilerAlojamientoDTO>> buscarReservasByUsername(@PathVariable("username") String username){
		return ResponseEntity.ok().body(this.alquilerAlojamientoService.buscarReservasByUsername(username));
	}
	
	/**
	 * [CONTROLLER]
	 * Métoodo que sirve para cancelar una reserva.
	 * @param idAlquilerAlojamiento
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericAPIMessageDTO> cancelarReserva(@PathVariable("id") Long idAlquilerAlojamiento){
		return ResponseEntity.ok().body(this.alquilerAlojamientoService.cancelarReserva(idAlquilerAlojamiento));
	}
}
