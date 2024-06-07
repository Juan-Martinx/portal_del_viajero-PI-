package com.pdv.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/public/")
	public ResponseEntity<List<ComodidadAlojamientoDTO>> buscarComodidadesAlojamientos(@RequestParam("txtNombre") String txtNombre,
			@RequestParam(name = "codigoComodidad", required = false) String codigoComodidad, @RequestParam(name = "tipoComodidadId", required = false, defaultValue = "-1") Integer tipoComodidadId, 
			@RequestParam(name = "codigoTipoComodidad", required = false) String codigoTipoComodidad,Pageable page){
		return ResponseEntity.ok().body(comodidadAlojamientoService.findComodidadesFromBuscador(txtNombre, codigoComodidad, tipoComodidadId, codigoTipoComodidad, page));
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ComodidadAlojamientoDTO> buscarComodidadAlojamientoPorCod(@PathVariable("codigo") String codigo){
		return ResponseEntity.ok().body(comodidadAlojamientoService.buscarComodidadAlojamientoPorCod(codigo));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@PostMapping
	public ResponseEntity<GenericAPIMessageDTO> crearComodidad(@RequestBody ComodidadAlojamientoDTO dto){
		return ResponseEntity.ok().body(comodidadAlojamientoService.crearComodidad(dto));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@PutMapping
	public ResponseEntity<GenericAPIMessageDTO> modificarComodidad(@RequestBody ComodidadAlojamientoDTO dto){
		return ResponseEntity.ok().body(comodidadAlojamientoService.modificarComodidad(dto));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("/{codigo}")
	public ResponseEntity<GenericAPIMessageDTO> eliminarComodidadAlojamientoPorCod(@PathVariable("codigo") String codigo){
		return ResponseEntity.ok().body(comodidadAlojamientoService.eliminarComodidad(codigo));
	}
}