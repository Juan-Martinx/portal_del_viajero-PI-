package com.pdv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping
	public ResponseEntity<UsuarioDTO> obtenerUsuarioLogueado(Authentication authentication){
		return ResponseEntity.ok().body(this.usuarioService.obtenerUsuarioLogueadoDto(authentication));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<GenericAPIMessageDTO> editarPerfilUsuario(@RequestBody UsuarioDTO dto){
		return ResponseEntity.ok().body(usuarioService.editarUsuario(dto));
	}
	
	@PostMapping("convertir-gestor")
	public ResponseEntity<GenericAPIMessageDTO> convertirUsuarioAGestor(@RequestParam("id") Long id){
		return ResponseEntity.ok().body(this.usuarioService.convertirUsuarioAGestor(id));
	}
	
}