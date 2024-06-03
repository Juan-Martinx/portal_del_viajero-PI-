package com.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/{id}")
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
	
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> aniadirAlojamiento(@RequestBody AlojamientoDTO dto, Authentication autenticacion){
		return ResponseEntity.ok().body(this.alojamientoService.aniadirAlojamiento(dto, autenticacion));
	}
	
	@PutMapping
	public ResponseEntity<GenericAPIMessageDTO> modificarAlojamiento(@RequestBody AlojamientoDTO dto){
		return ResponseEntity.ok().body(this.alojamientoService.modificarAlojamiento(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericAPIMessageDTO> eliminarAlojamiento(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(this.alojamientoService.eliminarAlojamiento(id));
	}
}
