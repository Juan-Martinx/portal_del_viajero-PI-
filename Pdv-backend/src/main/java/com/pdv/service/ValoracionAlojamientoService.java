package com.pdv.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.UsuarioDTO;
import com.pdv.dto.ValoracionAlojamientoDTO;
import com.pdv.model.Alojamiento;
import com.pdv.model.ValoracionAlojamiento;
import com.pdv.repository.AlojamientoRepository;
import com.pdv.repository.AlquilerAlojamientoRepository;
import com.pdv.repository.ValoracionAlojamientoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValoracionAlojamientoService {
	
	private final ValoracionAlojamientoRepository valoracionAlojamientoRepository;
	private final AlojamientoRepository alojamientoRepository;
	private final AlquilerAlojamientoRepository alquilerAlojamientoRepository;
	private final UsuarioService usuarioService;
	
	@Transactional
	public GenericAPIMessageDTO crearValoracion(ValoracionAlojamientoDTO dto, Authentication autenticacion) {
		
		Alojamiento alojamiento = this.alojamientoRepository.findById(dto.getAlojamientoId())
				.orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
		
		var usuario = this.usuarioService.obtenerUsuarioApp(autenticacion);
		var reservasOpt = this.alquilerAlojamientoRepository.findByIdUsuarioIdAndIdAlojamientoId(usuario.getId(), alojamiento.getId());
		var valoracionesOpt = this.valoracionAlojamientoRepository.findByIdUsuarioIdAndIdAlojamientoId(usuario.getId(), alojamiento.getId());
		
		if(reservasOpt.get().isEmpty()) {
			return GenericAPIMessageDTO.builder()
					.mensaje("¡No puedes valorar el alojamiento sin siquiera haber hecho una reserva!")
					.estado(HttpStatus.OK)
					.fechaYHora(LocalDateTime.now())
					.build();
		}
		
		if(!valoracionesOpt.get().isEmpty()) {
			return GenericAPIMessageDTO.builder()
					.mensaje("¡No puedes valorar dos veces el mismo alojamiento!")
					.estado(HttpStatus.OK)
					.fechaYHora(LocalDateTime.now())
					.build();
		}
		
		var jpa = ValoracionAlojamiento.builder()
				.idAlojamiento(alojamiento)
				.idUsuario(usuario)
				.puntuacion(dto.getPuntuacion())
				.txtAsunto(dto.getTxtAsunto())
				.txtMensaje(dto.getTxtMensaje())
				.build();
		
		this.valoracionAlojamientoRepository.save(jpa);
		
		return GenericAPIMessageDTO.builder()
				.mensaje("¡Se ha valorado el alojamiento con éxito!")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
	}

	public ValoracionAlojamientoDTO toDto (ValoracionAlojamiento jpa) {
		return ValoracionAlojamientoDTO.builder()
				.id(jpa.getId())
				.idUsuario(UsuarioDTO.builder()
						.id(jpa.getIdUsuario().getId())
						.username(jpa.getIdUsuario().getUsername())
						.numTelefono(jpa.getIdUsuario().getNumTelefono())
						.urlImagenUsuario(jpa.getIdUsuario().getUrlImagenUsuario())
						.build())
				.puntuacion(jpa.getPuntuacion())
				.txtAsunto(jpa.getTxtAsunto())
				.txtMensaje(jpa.getTxtMensaje())
				.build();
	}
}
