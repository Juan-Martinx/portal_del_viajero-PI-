package com.pdv.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pdv.dto.AlojamientoComodidadAlojamientoDTO;
import com.pdv.dto.AlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.UbicacionAlojamientoDTO;
import com.pdv.model.Alojamiento;
import com.pdv.model.AlojamientoComodidadAlojamiento;
import com.pdv.model.UbicacionAlojamiento;
import com.pdv.repository.AlojamientoComodidadAlojamientoRepository;
import com.pdv.repository.AlojamientoRepository;
import com.pdv.repository.ComodidadAlojamientoRepository;
import com.pdv.repository.UbicacionAlojamientoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlojamientoService {
	
	private final AlojamientoRepository alojamientoRepository;
	private final UsuarioService usuarioService;
	private final ComodidadAlojamientoRepository comodidadAlojamientoRepository;
	private final UbicacionAlojamientoRepository ubicacionAlojamientoRepository;
	private final AlojamientoComodidadAlojamientoRepository alojamientoComodidadAlojamientoRepository;
	private final AlojamientoComodidadAlojamientoService alojamientoComodidadAlojamientoService;
	
	public List<AlojamientoDTO> buscarAlojamientoUsuario(Authentication autenticacion) {
		var usuario = this.usuarioService.obtenerUsuarioApp(autenticacion);
		var alojamientosList = this.alojamientoRepository.findByIdUsuarioId(usuario.getId());
		var dtoList = new ArrayList<AlojamientoDTO>();
		if(alojamientosList.isPresent() && !alojamientosList.get().isEmpty()) {
			alojamientosList.get().forEach(jpa -> {
				dtoList.add(this.toDto(jpa));
			});
		}
		return dtoList;
	}
	
	public AlojamientoDTO buscarAlojamientoById(Long id) {
		var jpa = this.alojamientoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
		return this.toDto(jpa);
	}
	
	@Transactional
	public GenericAPIMessageDTO aniadirAlojamiento(AlojamientoDTO dto, Authentication autenticacion) {
	    
		// Crear el objeto Alojamiento
	    var jpa = Alojamiento.builder()
	            .idUsuario(usuarioService.obtenerUsuarioApp(autenticacion))
	            .txtNombre(dto.getTxtNombre())
	            .txtDescripcion(dto.getTxtDescripcion())
	            .numPlazaMin(dto.getNumPlazaMin())
	            .numPlazaMax(dto.getNumPlazaMax())
	            .numPrecioNoche(dto.getNumPrecioNoche())
	            .build();

	    // Crear UbicacionAlojamiento
	    var ubicacionJpa = UbicacionAlojamiento.builder()
	    		.idAlojamiento(jpa)
	            .codigoPostal(dto.getIdUbicacion().getCodigoPostal())
	            .lineaDireccion(dto.getIdUbicacion().getLineaDireccion())
	            .provincia(dto.getIdUbicacion().getProvincia())
	            .build();
	    

	    // Crear AlojamientoComodidadAlojamiento
	    var alojamientoComodidadesSet = new HashSet<AlojamientoComodidadAlojamiento>();
	    dto.getIdComodidades().forEach(comodidadId -> {
	        var jpaComodidad = this.comodidadAlojamientoRepository.findById(comodidadId)
	                .orElseThrow(() -> new RuntimeException("Comodidad no encontrada"));
	        var alojamientoComodidadAlojamiento = AlojamientoComodidadAlojamiento.builder()
	                .idAlojamiento(jpa)
	                .idComodidadAlojamiento(jpaComodidad)
	                .build();
	        alojamientoComodidadesSet.add(alojamientoComodidadAlojamiento);
	    });
	    
	    jpa.setIdUbicacion(ubicacionJpa);
	    jpa.setIdAlojamientoComodidades(alojamientoComodidadesSet);
	    
	    this.alojamientoRepository.save(jpa);
	    
	    return GenericAPIMessageDTO.builder()
	            .estado(HttpStatus.OK)
	            .mensaje("¡Alojamiento creado con éxito!")
	            .fechaYHora(LocalDateTime.now())
	            .build();
	}
	
	public AlojamientoDTO toDto (Alojamiento jpa) {
		var comodidadesList = new HashSet<AlojamientoComodidadAlojamientoDTO>();
		jpa.getIdAlojamientoComodidades().forEach(comodidad -> {
			comodidadesList.add(alojamientoComodidadAlojamientoService.toDtoWithoutAlojamiento(comodidad));
		});
		var dto = AlojamientoDTO.builder()
				.id(jpa.getId())
	            .txtNombre(jpa.getTxtNombre())
	            .txtDescripcion(jpa.getTxtDescripcion())
	            .numPlazaMin(jpa.getNumPlazaMin())
	            .numPlazaMax(jpa.getNumPlazaMax())
	            .numPrecioNoche(jpa.getNumPrecioNoche())
	            .idUbicacion(UbicacionAlojamientoDTO.builder()
	            		.ciudad(jpa.getIdUbicacion().getCiudad())
	            		.provincia(jpa.getIdUbicacion().getProvincia())
	            		.lineaDireccion(jpa.getIdUbicacion().getLineaDireccion())
	            		.codigoPostal(jpa.getIdUbicacion().getCodigoPostal())
	            		.build())
	            .idAlojamientoComodidades(comodidadesList)
	            .build();
		return dto;
	}

}
