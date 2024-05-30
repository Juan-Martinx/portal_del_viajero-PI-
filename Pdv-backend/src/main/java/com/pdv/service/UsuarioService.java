package com.pdv.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.UsuarioDTO;
import com.pdv.enums.CodPerfiles;
import com.pdv.model.Usuario;
import com.pdv.repository.GoogleUserRepository;
import com.pdv.repository.PerfilRepository;
import com.pdv.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final GoogleUserRepository googleUserRepository;
	private final PerfilRepository perfilRepository;
	private final PerfilService perfilService;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * Realiza una búsqueda páginada de los usuarios con la 
	 * opción de filtrar por nombre de usuario, dni, email y teléfono.
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<UsuarioDTO> buscarUsuariosAplicacion(UsuarioDTO dto, Pageable page) {
		var usuarios = this.usuarioRepository.findUsuariosFromBuscador(dto.getUsername(), dto.getTxtDni(), dto.getTxtEmail(), page).toList();
		var dtoList = new ArrayList<UsuarioDTO>();
		if(!usuarios.isEmpty()) {
			usuarios.forEach(usuario -> {
				var usuarioDto = UsuarioDTO.builder()
						.id(usuario.getId())
						.username(usuario.getUsername())
						.build();
				dtoList.add(usuarioDto);
			});
		}
		return dtoList;
	}
	
	/**
	 * Método que sirve para editar a un perfil de usuario dentro de la aplicación.
	 * @param dto
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO editarUsuario(UsuarioDTO dto) {

		var jpaOpt = this.usuarioRepository.findById(dto.getId());
		var mensaje = new GenericAPIMessageDTO();

		if (jpaOpt.isPresent()) {

			var jpa = jpaOpt.get();
			
			try {
				jpa.setUsername(dto.getUsername());
				jpa.setTxtDni(dto.getTxtDni());
				jpa.setTxtDescripcion(dto.getTxtDescripcion());
				jpa.setNumTelefono(dto.getNumTelefono());
				
			}catch(DataIntegrityViolationException e) {
				e.printStackTrace();
			}		

			mensaje = GenericAPIMessageDTO.builder()
					.mensaje("Usuario Editado con éxito")
					.estado(HttpStatus.OK)
					.fechaYHora(LocalDateTime.now()).build();
		} else {
			mensaje = GenericAPIMessageDTO.builder()
					.mensaje("Hubo un problema encontrando al usuario")
					.estado(HttpStatus.NOT_FOUND)
					.fechaYHora(LocalDateTime.now())
					.build();
		}

		return mensaje;
	}
	
	/**
	 * Convierte a un usuario en Gestor según un id que se le pasa
	 * @param id
	 * @return
	 */
	public GenericAPIMessageDTO convertirUsuarioAGestor(Long id) {
		
		var mensaje = new GenericAPIMessageDTO();
		mensaje.setFechaYHora(LocalDateTime.now());
		var jpaOpt = this.usuarioRepository.findById(id);
		if(jpaOpt.isPresent()) {
			var jpa = jpaOpt.get();
			var perfiles = jpa.getIdPerfiles();
			var perfilGestor = perfilRepository.findByCodPerfil(CodPerfiles.PERFIL_GESTOR)
					.orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
			
			mensaje.setEstado(HttpStatus.OK);
			
			if(perfiles.contains(perfilGestor)) {
				mensaje.setMensaje("Tu cuenta ya tenía asignado el perfil de gestor");

			}else {
				perfiles.add(perfilGestor);
				jpa.setIdPerfiles(perfiles);
				this.usuarioRepository.save(jpa);
				mensaje.setMensaje("Operación realizada con éxito: ¡Ya podrá poner en alquiler sus alojamientos!");

			}
			
		}else {
			mensaje.setMensaje("Hubo un problema encontrando al usuario");
			mensaje.setEstado(HttpStatus.NOT_FOUND);
		}
		
		return mensaje;
	}
	
	/**
	 * Devuelve el usuario en su versión DTO.
	 * @param autentificacion
	 * @return
	 */
	public UsuarioDTO obtenerUsuarioLogueadoDto(Authentication autentificacion) {
		
		var usuarioJpa = this.obtenerUsuarioApp(autentificacion);
		
		var usuarioDto = UsuarioDTO.builder()
				.id(usuarioJpa.getId())
				.username(usuarioJpa.getUsername())
				.txtDni(usuarioJpa.getTxtDni())
				.txtDescripcion(usuarioJpa.getTxtDescripcion())
				.numTelefono(usuarioJpa.getNumTelefono())
				.txtEmail(usuarioJpa.getTxtEmail())
				.perfiles(perfilService.getPerfilesFromUserJpa(usuarioJpa));
		
		return usuarioDto.build();
	}
	
	/**
	 * Método que detecta si el usuario que se encuentra
	 * logueado dentro de la aplicación mediante una cuenta
	 * de google o manualmente. En caso de ser de google,
	 * lo que va a hacer es encontrar su usuario interno
	 * dentro de la aplicación (ya que los usuarios registrados
	 * con google tienen dos cuentas usuario) y devolverá el
	 * usuario de la app correspondiente.
	 * @param autentificacion
	 * @return
	 */
	public Usuario obtenerUsuarioApp(Authentication autentificacion){
		
        boolean isGoogleUser =  autentificacion.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> CodPerfiles.OIDC_USER.name().equals(authority));
        
        if(isGoogleUser) {
        	var googUser = this.googleUserRepository.findByEmail(autentificacion.getName())
        		.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        	return googUser.getAppUser();
        }else {
        	var appUser = this.usuarioRepository.findByUsername(autentificacion.getName())
        			.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        	return appUser;
        }
                
	}
}