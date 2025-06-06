package com.pdv.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
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
	
	/**
	 * [CONTROLLER]
	 * Método que devuelve la información del
	 * usuario que se encuentra logueado en la app.
	 * @param authentication
	 * @return
	 */
	@GetMapping
	public ResponseEntity<UsuarioDTO> obtenerUsuarioLogueado(Authentication authentication){
		return ResponseEntity.ok().body(this.usuarioService.obtenerUsuarioLogueadoDto(authentication));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que obtiene los usuarios de la app
	 * en base a los siguientes filtros:
	 * @param username
	 * @param txtDni
	 * @param txtEmail
	 * @param page
	 * @return
	 */
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
	
	/**
	 * [CONTROLLER]
	 * Método que busca a un usuario según su username
	 * @param username
	 * @return
	 */
	@GetMapping("/public/{username}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorUsername(@PathVariable String username){
		return ResponseEntity.ok().body(this.usuarioService.buscarUsuarioPorUsername(username));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que busca un usuario y da información adicional
	 * sobre él para su gestión como usuario administrador.
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@GetMapping("/admin/{username}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorUsernameByAdmin(@PathVariable String username){
		return ResponseEntity.ok().body(this.usuarioService.buscarUsuarioPorUsernameByAdmin(username));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que permite editar a un usuario de la aplicación.
	 * @param dto
	 * @return
	 */
	@PutMapping("/editar")
	public ResponseEntity<GenericAPIMessageDTO> editarPerfilUsuario(@RequestBody UsuarioDTO dto){
		return ResponseEntity.ok().body(usuarioService.editarUsuario(dto));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que permite editar a otros usuarios.
	 * @param username
	 * @param dto
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@PutMapping("/editar-otro/{username}")
	public ResponseEntity<GenericAPIMessageDTO> editarPerfilUsuarioOtro(@PathVariable("username") String username,@RequestBody UsuarioDTO dto){
		return ResponseEntity.ok().body(usuarioService.editarUsuarioByUsername(username,dto));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que permite a un usuario convertirse
	 * en gestor de alojamientos.
	 * @param id
	 * @return
	 */
	@PostMapping("convertir-gestor")
	public ResponseEntity<GenericAPIMessageDTO> convertirUsuarioAGestor(@RequestParam("id") Long id){
		return ResponseEntity.ok().body(this.usuarioService.convertirUsuarioAGestor(id));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que permite almacenar la dirección URL
	 * de la foto de perfil subida por el usuario a través
	 * del mediaController.
	 * @param params
	 * @param autenticacion
	 * @return
	 */
	@PostMapping("/foto-perfil")
	public ResponseEntity<GenericAPIMessageDTO> subirFotoPerfil(@RequestBody Map<String, String> params, Authentication autenticacion){
		return ResponseEntity.ok().body(this.usuarioService.subirFotoPerfil(params.get("url"), autenticacion));
	}
	
	/**
	 * [CONTROLLER]
	 * Método que permite eliminar a un usuario de la
	 * aplicación.
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("/{username}")
	public ResponseEntity<GenericAPIMessageDTO> eliminarUsuario(@PathVariable("username") String username){
		return ResponseEntity.ok().body(this.usuarioService.eliminarUsuario(username));
	}
}