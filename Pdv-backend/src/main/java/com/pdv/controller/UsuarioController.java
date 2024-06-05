package com.pdv.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@GetMapping("/buscar")
	public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosApp(@RequestParam("username") String username,
			@RequestParam("txtDni") String txtDni, @RequestParam("txtEmail") String txtEmail, Pageable page){
		var dto = UsuarioDTO.builder()
				.username(username)
				.txtDni(txtDni)
				.txtEmail(txtEmail);
		return ResponseEntity.ok().body(this.usuarioService.buscarUsuariosAplicacion(dto.build(), page));
	}
	
	@GetMapping("/public/{username}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorUsername(@PathVariable String username){
		return ResponseEntity.ok().body(this.usuarioService.buscarUsuarioPorUsername(username));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<GenericAPIMessageDTO> editarPerfilUsuario(@RequestBody UsuarioDTO dto){
		return ResponseEntity.ok().body(usuarioService.editarUsuario(dto));
	}
	
	@PostMapping("convertir-gestor")
	public ResponseEntity<GenericAPIMessageDTO> convertirUsuarioAGestor(@RequestParam("id") Long id){
		return ResponseEntity.ok().body(this.usuarioService.convertirUsuarioAGestor(id));
	}
	
	@PostMapping("/foto-perfil")
	public ResponseEntity<GenericAPIMessageDTO> subirFotoPerfil(@RequestBody Map<String, String> params, Authentication autenticacion){
		return ResponseEntity.ok().body(this.usuarioService.subirFotoPerfil(params.get("url"), autenticacion));
	}
	
}