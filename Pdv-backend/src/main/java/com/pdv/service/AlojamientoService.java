package com.pdv.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pdv.dto.AlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
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
	    
	    // Guardar el Alojamiento para que tenga un ID generado
	    this.alojamientoRepository.save(jpa);

	    // Crear y guardar UbicacionAlojamiento
	    var ubicacionJpa = UbicacionAlojamiento.builder()
	            .idAlojamiento(jpa)
	            .codigoPostal(dto.getIdUbicacion().getCodigoPostal())
	            .lineaDireccion(dto.getIdUbicacion().getLineaDireccion())
	            .provincia(dto.getIdUbicacion().getProvincia())
	            .build();
	    this.ubicacionAlojamientoRepository.save(ubicacionJpa);

	    // Crear y guardar AlojamientoComodidadAlojamiento
	    var alojamientoComodidadesSet = new HashSet<AlojamientoComodidadAlojamiento>();
	    dto.getIdComodidades().forEach(comodidadId -> {
	        var jpaComodidad = this.comodidadAlojamientoRepository.findById(comodidadId)
	                .orElseThrow(() -> new RuntimeException("Comodidad no encontrada"));
	        var alojamientoComodidadAlojamiento = AlojamientoComodidadAlojamiento.builder()
	                .idAlojamiento(jpa)
	                .idComodidadAlojamiento(jpaComodidad)
	                .build();
	        this.alojamientoComodidadAlojamientoRepository.save(alojamientoComodidadAlojamiento);
	        alojamientoComodidadesSet.add(alojamientoComodidadAlojamiento);
	    });
	    
	    //No se guarda de nuevo el alojamiento ni se setean las propiedades debido
	    //a que esto causaría un bucle infinito de objetos alojamiento, sin embargo,
	    //todo se persiste de manera correcta en la base de datos
	    
	    return GenericAPIMessageDTO.builder()
	            .estado(HttpStatus.OK)
	            .mensaje("¡Alojamiento creado con éxito!")
	            .fechaYHora(LocalDateTime.now())
	            .build();
	}

}
