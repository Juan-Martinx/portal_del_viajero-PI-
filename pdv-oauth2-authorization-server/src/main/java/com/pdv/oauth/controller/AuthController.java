package com.pdv.oauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.oauth.commons.PathCommons;
import com.pdv.oauth.dto.AppUsuarioDTO;
import com.pdv.oauth.dto.GenericAPIMessageDTO;
import com.pdv.oauth.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PathCommons.AUTH)
@RequiredArgsConstructor
public class AuthController {

	private final UsuarioService usuarioService;
		
	@PostMapping("/crear")
	public ResponseEntity<GenericAPIMessageDTO> crearUsuario(@RequestBody AppUsuarioDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(dto));
	}
	
}
