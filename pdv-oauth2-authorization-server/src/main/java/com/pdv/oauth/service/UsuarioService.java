package com.pdv.oauth.service;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdv.oauth.dto.AppUsuarioDTO;
import com.pdv.oauth.dto.GenericAPIMessageDTO;
import com.pdv.oauth.enums.CodPerfiles;
import com.pdv.oauth.model.GoogleUser;
import com.pdv.oauth.model.Perfil;
import com.pdv.oauth.model.Usuario;
import com.pdv.oauth.repository.GoogleUserRepository;
import com.pdv.oauth.repository.PerfilRepository;
import com.pdv.oauth.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Servicio para gestionar usuarios en el sistema.
 * Proporciona métodos para la creación de usuarios y la generación de usuarios a través de OAuth2.
 */
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final GoogleUserRepository googleUserRepository;
	private final PerfilRepository perfilRepository;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * Crea un nuevo usuario en el sistema.
	 * 
	 * @param dto Los datos del usuario a crear
	 * @return Un objeto GenericAPIMessageDTO con el resultado de la operación
	 */
	@Transactional
	public GenericAPIMessageDTO crearUsuario(AppUsuarioDTO dto) {
		
		var nuevoUsuario = Usuario.builder()
				.username(dto.username())
				.password(passwordEncoder.encode(dto.password()))
				.txtEmail(dto.txtEmail())
				.txtDni(dto.txtDni())
				.numTelefono(dto.numTelefono())
				.build();
		
		var perfiles = new HashSet<Perfil>();
		dto.perfiles().forEach(p -> {
			var perfil = perfilRepository.findByCodPerfil(CodPerfiles.valueOf(p))
					.orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
			perfiles.add(perfil);
		});
		
		nuevoUsuario.setIdPerfiles(perfiles);
		usuarioRepository.save(nuevoUsuario);
		
		return GenericAPIMessageDTO.builder()
				.mensaje("Usuario Creado Con Éxito")
				.fechaYHora(LocalDateTime.now())
				.estado(HttpStatus.CREATED)
				.build();
	}
	
	/**
	 * Genera un nuevo usuario a través de OAuth2.
	 * 
	 * @param googleUser El usuario de Google del que se generará el usuario OAuth2
	 * @return Un objeto GenericAPIMessageDTO con el resultado de la operación
	 */
	public GenericAPIMessageDTO generarUsuarioParaOAuth2(GoogleUser googleUser) {
		
		var nuevoUsuario = Usuario.builder()
				.username("usuario" + (obtenerIdUltimoUsuarioCreado() + 1))
				.password(passwordEncoder.encode("usdh127*ks"))
				.txtEmail(googleUser.getEmail())
				.build();
		
		var perfiles = new HashSet<Perfil>();
		perfiles.add(perfilRepository.findByCodPerfil(CodPerfiles.PERFIL_CLIENTE).orElseThrow(() -> new RuntimeException("Perfil no encontrado")));
		nuevoUsuario.setIdPerfiles(perfiles);
		
		googleUser.setAppUser(nuevoUsuario);
		
		googleUserRepository.save(googleUser);
		
		return GenericAPIMessageDTO.builder()
				.mensaje("Usuario Creado Con Éxito")
				.fechaYHora(LocalDateTime.now())
				.estado(HttpStatus.CREATED)
				.build();
	}
	
	/**
	 * Obtiene el ID del último usuario creado en la base de datos.
	 * 
	 * @return El ID del último usuario creado
	 */
	@Transactional
    private Long obtenerIdUltimoUsuarioCreado() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 1, sort);
        Page<Usuario> usuariosPage = usuarioRepository.findUsuarioByPage(pageable);
        return usuariosPage.hasContent() ? usuariosPage.getContent().get(0).getId() : 0;
    }
	
}