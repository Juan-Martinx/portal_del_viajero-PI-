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
	
	@GetMapping
	public ResponseEntity<List<AlojamientoDTO>> buscarAlojamientoUsuario(Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoUsuario(autenticacion));
	}
	
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
	
	@GetMapping("/public/{id}")
	public ResponseEntity<AlojamientoDTO> buscarAlojamientoById(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoById(id));
	}
	
	@GetMapping("/gestion/{id}")
	public ResponseEntity<AlojamientoDTO> buscarAlojamientoByIdForGestion(@PathVariable("id") Long id, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoByIdForGestion(id, autenticacion));
	}
	
	@GetMapping("/public/username/{username}")
	public ResponseEntity<List<AlojamientoDTO>> buscarAlojamientoByUsername(@PathVariable("username") String username){
		return ResponseEntity.ok().body(this.alojamientoService.buscarAlojamientoByUsername(username));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> aniadirAlojamiento(@RequestBody AlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.aniadirAlojamiento(dto, autenticacion));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@PutMapping
	public ResponseEntity<GenericAPIMessageDTO> modificarAlojamiento(@RequestBody AlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.modificarAlojamiento(dto, autenticacion));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_GESTOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericAPIMessageDTO> eliminarAlojamiento(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(this.alojamientoService.eliminarAlojamiento(id));
	}
}
