package com.pdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.TipoComodidadDTO;
import com.pdv.service.TipoComodidadService;

import lombok.RequiredArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.TIPO_COMODIDAD)
@RequiredArgsConstructor
public class TipoComodidadController {
	
	private final TipoComodidadService tipoComodidadService;
	
	@GetMapping
	public ResponseEntity<List<TipoComodidadDTO>> buscarTiposComodidades(){
		return ResponseEntity.ok().body(this.tipoComodidadService.buscarTodosTiposComodidades());
	}
}
