package com.pdv.oauth.service;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdv.oauth.dto.AppUsuarioDTO;
import com.pdv.oauth.dto.GenericAPIMessageDTO;
import com.pdv.oauth.enums.CodPerfiles;
import com.pdv.oauth.model.Perfil;
import com.pdv.oauth.model.Usuario;
import com.pdv.oauth.repository.PerfilRepository;
import com.pdv.oauth.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PerfilRepository perfilRepository;
	private final PasswordEncoder passwordEncoder;
	
	public GenericAPIMessageDTO crearUsuario(AppUsuarioDTO dto) {
		
		var nuevoUsuario = Usuario.builder()
				.username(dto.username())
				.password(passwordEncoder.encode(dto.password()))
				.build();
		
		var perfiles = new HashSet<Perfil>();
		dto.perfiles().forEach(p -> {
			var perfil = perfilRepository.findByCodPerfil(CodPerfiles.valueOf(p))
					.orElseThrow(() -> new RuntimeException("Rol no encontrado"));
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
	
}