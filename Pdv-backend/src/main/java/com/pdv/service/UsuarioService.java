package com.pdv.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.UsuarioDTO;
import com.pdv.enums.CodPerfiles;
import com.pdv.model.Usuario;
import com.pdv.repository.AlojamientoRepository;
import com.pdv.repository.AlquilerAlojamientoRepository;
import com.pdv.repository.GoogleUserRepository;
import com.pdv.repository.PerfilRepository;
import com.pdv.repository.UsuarioRepository;
import com.pdv.repository.ValoracionAlojamientoRepository;

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
	private final ValoracionAlojamientoRepository valoracionAlojamientoRepository;
	private final AlquilerAlojamientoRepository alquilerAlojamientoRepository;
	private final AlojamientoRepository alojamientoRepository;
	
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
						.urlImagenUsuario(usuario.getUrlImagenUsuario())
						.username(usuario.getUsername())
						.build();
				dtoList.add(usuarioDto);
			});
		}
		return dtoList;
	}
	
	/**
	 * Realiza una búsqueda de un usuario por su username.
	 * @param username
	 * @return
	 */
	public UsuarioDTO buscarUsuarioPorUsername(String username) {
		var jpaOpt = this.usuarioRepository.findByUsername(username);
		var dto = new UsuarioDTO();
		if(jpaOpt.isPresent()) {
			var jpa = jpaOpt.get();
			dto = UsuarioDTO.builder()
					.username(username)
					.urlImagenUsuario(jpa.getUrlImagenUsuario())
					.txtEmail(jpa.getTxtEmail())
					.numTelefono(jpa.getNumTelefono())
					.txtDescripcion(jpa.getTxtDescripcion())
					.build();
		}
		return dto;
	}
	
	/**
	 * Realiza una búsqueda de usuario pero proporcionando más información,
	 * esto se usa para administrar a los usuarios.
	 * @param username
	 * @return
	 */
	public UsuarioDTO buscarUsuarioPorUsernameByAdmin(String username) {
		var jpaOpt = this.usuarioRepository.findByUsername(username);
		var dto = new UsuarioDTO();
		if(jpaOpt.isPresent()) {
			var jpa = jpaOpt.get();
			dto = UsuarioDTO.builder()
					.username(username)
					.txtDni(jpa.getTxtDni())
					.urlImagenUsuario(jpa.getUrlImagenUsuario())
					.txtEmail(jpa.getTxtEmail())
					.numTelefono(jpa.getNumTelefono())
					.txtDescripcion(jpa.getTxtDescripcion())
					.build();
		}
		return dto;
	}
	
	/**
	 * Elimina a un usuario de la aplicación.
	 * @param username
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO eliminarUsuario(String username) {
		var jpa = this.usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		this.usuarioRepository.deleteAllPerfilesRelationshipByIdUsuario(jpa.getId());
		
		jpa.getIdAlojamiento().forEach(alojamiento -> {
			alojamientoRepository.deleteById(alojamiento.getId());
		});
		jpa.setIdAlojamiento(null);
		
		jpa.getIdAlquileres().forEach(alquiler -> {
			alquilerAlojamientoRepository.deleteById(alquiler.getId());
		});
		jpa.setIdAlquileres(null);
		
		this.valoracionAlojamientoRepository.deleteByIdUsuario(jpa.getId());
		
		jpa.setIdValoracionesAlojamientos(null);

		this.usuarioRepository.deleteById(jpa.getId());
		return GenericAPIMessageDTO
				.builder()
				.mensaje("Usuario eliminado con éxito")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
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
				this.usuarioRepository.save(jpa);
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
	 * Método que sirve para editar un perfil de otros usuarios dentro de la aplicación.
	 * Este método solo puede ser iniciado por alguien con perfil de admin.
	 * @param username
	 * @param dto
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO editarUsuarioByUsername(String username, UsuarioDTO dto) {

		var jpaOpt = this.usuarioRepository.findByUsername(username);
		var mensaje = new GenericAPIMessageDTO();

		if (jpaOpt.isPresent()) {

			var jpa = jpaOpt.get();
			
			try {
				jpa.setUsername(dto.getUsername());
				jpa.setTxtDni(dto.getTxtDni());
				jpa.setTxtDescripcion(dto.getTxtDescripcion());
				jpa.setNumTelefono(dto.getNumTelefono());
				this.usuarioRepository.save(jpa);
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
	 * Método que sirve para guardar la URL de la ruta
	 * dónde se encuentra subida la foto de perfil del usuario.
	 * @param url
	 * @param autenticacion
	 * @return
	 */
	public GenericAPIMessageDTO subirFotoPerfil(String url, Authentication autenticacion) {
		
		var usuario = this.obtenerUsuarioApp(autenticacion);
		usuario.setUrlImagenUsuario(url);
		this.usuarioRepository.save(usuario);
		
		return GenericAPIMessageDTO.builder()
				.mensaje("Imágen subida con éxito")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
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
				.urlImagenUsuario(usuarioJpa.getUrlImagenUsuario())
				.username(usuarioJpa.getUsername())
				.txtDni(usuarioJpa.getTxtDni())
				.txtDescripcion(usuarioJpa.getTxtDescripcion())
				.numTelefono(usuarioJpa.getNumTelefono())
				.txtEmail(usuarioJpa.getTxtEmail())
				.perfiles(perfilService.getPerfilesFromUserJpa(usuarioJpa));
		
		return usuarioDto.build();
	}
	
	/**
	 * Método que detecta si el usuario se encuentra
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