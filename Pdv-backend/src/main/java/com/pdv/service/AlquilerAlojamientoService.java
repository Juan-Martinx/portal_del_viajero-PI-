package com.pdv.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pdv.dto.AlquilerAlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.model.AlojamientoDiasOcupados;
import com.pdv.model.AlquilerAlojamiento;
import com.pdv.repository.AlojamientoDiasOcupadosRepository;
import com.pdv.repository.AlojamientoRepository;
import com.pdv.repository.AlquilerAlojamientoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlquilerAlojamientoService {

	private final UsuarioService usuarioService;
	private final AlojamientoRepository alojamientoRepository;
	private final AlquilerAlojamientoRepository alquilerAlojamientoRepository;
	private final AlojamientoDiasOcupadosRepository alojamientoDiasOcupadosRepository;
	
	@Transactional
	public GenericAPIMessageDTO realizarReserva(AlquilerAlojamientoDTO dto, Authentication autenticacion) {
		
		var usuario = this.usuarioService.obtenerUsuarioApp(autenticacion);
		var alojamiento = alojamientoRepository.getReferenceById(dto.getIdAlojamiento());
		
		long numDiasReservados = ChronoUnit.DAYS.between(dto.getFechaInicioAlquiler(), dto.getFechaFinAlquiler());
		Double precioTotal = numDiasReservados * alojamiento.getNumPrecioNoche();
		
		//Comprobar que la fecha de salida no sea anterior a la de llegada
		if(dto.getFechaFinAlquiler().isBefore(dto.getFechaInicioAlquiler())) {
    		return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("ERROR: ¡La fecha de llegada debe ser anterior a la de salida!")
    				.fechaYHora(LocalDateTime.now()).build();
		}
		
		//Comprobar que la fecha sea igual o posterior a la actual
		if(!(dto.getFechaInicioAlquiler().isAfter(LocalDate.now()) || dto.getFechaInicioAlquiler().isEqual(LocalDate.now()))) {
    		return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("ERROR: ¡La fecha debe ser anterior o igual a la actual!")
    				.fechaYHora(LocalDateTime.now()).build();
		}
		
	    // Comprobar que ese día no haya una reserva
		// En caso de no estar ocupado, ese día se ocupa
	    for (long i = 0; i <= numDiasReservados; i++) {
	        LocalDate fechaDiaReserva = dto.getFechaInicioAlquiler().plusDays(i);
	        var isDiaOcupado = this.alojamientoDiasOcupadosRepository.findByDiaAndMesAndAnyoAndIdAlojamientoId(fechaDiaReserva.getDayOfMonth(), fechaDiaReserva.getMonthValue(), fechaDiaReserva.getYear(), dto.getIdAlojamiento()).isPresent();
	        if(isDiaOcupado) {
	    		return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("ERROR: En esta fecha ya hay otra reserva realizada")
	    				.fechaYHora(LocalDateTime.now()).build();
	        }else {
	        	var diaOcupadoJpa = AlojamientoDiasOcupados.builder()
	        			.dia(fechaDiaReserva.getDayOfMonth())
	        			.mes(fechaDiaReserva.getMonthValue())
	        			.anyo(fechaDiaReserva.getYear())
	        			.idAlojamiento(alojamiento)
	        			.build();
	        	this.alojamientoDiasOcupadosRepository.save(diaOcupadoJpa);
	        }
	    }
	    
		var reservaJpa = AlquilerAlojamiento.builder()
				.fechaInicioAlquiler(dto.getFechaInicioAlquiler())
				.fechaFinAlquiler(dto.getFechaFinAlquiler())
				.idAlojamiento(alojamiento)
				.idUsuario(usuario)
				.numPlazasReservadas(dto.getNumPlazasReservadas())
				.precioTotalAlquiler(precioTotal )
				.build();
		
		this.alquilerAlojamientoRepository.save(reservaJpa);
		
		return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("¡Reserva realizada con éxito!")
				.fechaYHora(LocalDateTime.now()).build();
	}
}
