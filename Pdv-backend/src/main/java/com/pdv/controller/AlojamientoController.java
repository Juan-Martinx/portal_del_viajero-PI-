package com.pdv.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.AlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.service.AlojamientoService;

import lombok.RequiredArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.ALOJAMIENTO)
@RequiredArgsConstructor
public class AlojamientoController {
	
	private final AlojamientoService alojamientoService;
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar los alojamientos asociados
	 * al usuario que se encuentra autenticado en la aplicación.
	 * @param autenticacion
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<AlojamientoDTO>> buscarAlojamientoUsuario(Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoUsuario(autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar todos los alojamientos disponibles
	 * en la aplicación pasándole cómo parámetros los siguientes filtros:
	 * @param idComodidades
	 * @param numPrecioNocheMin
	 * @param numPrecioNocheMax
	 * @param fechaLlegada
	 * @param fechaSalida
	 * @param provincia
	 * @param page
	 * @return
	 */
	@GetMapping("/public/findWithFilters")
	public ResponseEntity<List<AlojamientoDTO>> buscarAlojamientoWithFilters(@RequestParam(name = "idComodidades", required = false) List<Long> idComodidades,
			@RequestParam(name = "numPrecioNocheMin", required = false, defaultValue = "-1.00") Double numPrecioNocheMin,
			@RequestParam(name = "numPrecioNocheMax", required = false, defaultValue = "999999.00") Double numPrecioNocheMax,
			@RequestParam(name = "fechaLlegada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada, 
			@RequestParam(name = "fechaSalida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
			@RequestParam(name = "provincia", required = false) String provincia,
			Pageable page){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoWithFilters(provincia, idComodidades, numPrecioNocheMin, numPrecioNocheMax, fechaLlegada, fechaSalida, page));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar un alojamiento según su id
	 * @param id
	 * @return
	 */
	@GetMapping("/public/{id}")
	public ResponseEntity<AlojamientoDTO> buscarAlojamientoById(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoById(id));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para buscar un alojamiento por su id
	 * pero destinado a la gestión (La diferencia con el anterior
	 * es que si el id del usuario asociado con el alojamiento no coincide
	 * con el id que devuelve la autentificación, este alojamiento no se
	 * puede mostrar/editar)
	 * @param id
	 * @param autenticacion
	 * @return
	 */
	@GetMapping("/gestion/{id}")
	public ResponseEntity<AlojamientoDTO> buscarAlojamientoByIdForGestion(@PathVariable("id") Long id, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoByIdForGestion(id, autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que encuentra todos los alojamientos de un usuario
	 * dado su username.
	 * @param username
	 * @return
	 */
	@GetMapping("/public/username/{username}")
	public ResponseEntity<List<AlojamientoDTO>> buscarAlojamientoByUsername(@PathVariable("username") String username){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoByUsername(username));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para crear un alojamiento.
	 * @param dto
	 * @param autenticacion
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> aniadirAlojamiento(@RequestBody AlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.aniadirAlojamiento(dto, autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para actualizar las características
	 * de un alojamiento.
	 * @param dto
	 * @param autenticacion
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@PutMapping
	public ResponseEntity<GenericAPIMessageDTO> modificarAlojamiento(@RequestBody AlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.modificarAlojamiento(dto, autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para eliminar un alojamiento.
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericAPIMessageDTO> eliminarAlojamiento(@PathVariable("id") Long id, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.eliminarAlojamiento(id, autenticacion));
	}
}
