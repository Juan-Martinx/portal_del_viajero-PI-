package com.pdv.service;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.UsuarioDTO;
import com.pdv.enums.CodPerfiles;
import com.pdv.model.Perfil;
import com.pdv.model.Usuario;
import com.pdv.repository.PerfilRepository;
import com.pdv.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PerfilRepository perfilRepository;
	private final PasswordEncoder passwordEncoder;
	
	public GenericAPIMessageDTO crearUsuario(UsuarioDTO usuarioDTO) {
		
		var nuevoUsuario = Usuario.builder()
				.username(usuarioDTO.getUsername())
				.password(passwordEncoder.encode(usuarioDTO.getPassword()))
				.build();
		
		var perfiles = new HashSet<Perfil>();
		usuarioDTO.getPerfiles().forEach(p -> {
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