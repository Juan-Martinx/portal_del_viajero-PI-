package com.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.ComodidadAlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.service.ComodidadAlojamientoService;

import lombok.RequiredArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.COMODIDAD_ALOJAMIENTO)
@RequiredArgsConstructor
public class ComodidadAlojamientoController {

	private final ComodidadAlojamientoService comodidadAlojamientoService;
	
	@GetMapping
	public ResponseEntity<List<ComodidadAlojamientoDTO>> buscarComodidadesAlojamientos(){
		return ResponseEntity.ok().body(comodidadAlojamientoService.findAll());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ComodidadAlojamientoDTO> buscarComodidadAlojamientoPorCod(@PathVariable("codigo") String codigo){
		return ResponseEntity.ok().body(comodidadAlojamientoService.buscarComodidadAlojamientoPorCod(codigo));
	}
	
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> crearComodidad(@RequestBody ComodidadAlojamientoDTO dto){
		return ResponseEntity.ok().body(comodidadAlojamientoService.crearComodidad(dto));
	}
	
	@PutMapping
	public ResponseEntity<GenericAPIMessageDTO> modificarComodidad(@RequestBody ComodidadAlojamientoDTO dto){
		return ResponseEntity.ok().body(comodidadAlojamientoService.modificarComodidad(dto));
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<GenericAPIMessageDTO> eliminarComodidadAlojamientoPorCod(@PathVariable("codigo") String codigo){
		return ResponseEntity.ok().body(comodidadAlojamientoService.eliminarComodidad(codigo));
	}
}