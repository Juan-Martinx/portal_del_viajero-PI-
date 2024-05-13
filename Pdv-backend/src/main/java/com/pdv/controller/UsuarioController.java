package com.pdv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.UsuarioDTO;
import com.pdv.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.USUARIOS)
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@PostMapping("/crear")
	public ResponseEntity<GenericAPIMessageDTO> crearUsuario(@RequestBody UsuarioDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(dto));
	}
}
