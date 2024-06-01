package com.pdv.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pdv.dto.ComodidadAlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.TipoComodidadDTO;
import com.pdv.model.ComodidadAlojamiento;
import com.pdv.repository.ComodidadAlojamientoRepository;
import com.pdv.repository.TipoComodidadRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComodidadAlojamientoService {
	
	private final ComodidadAlojamientoRepository comodidadAlojamientoRepository;
	private final TipoComodidadRepository tipoComodidadRepository;
	
	@Transactional
	public GenericAPIMessageDTO crearComodidad(ComodidadAlojamientoDTO dto) {
		
		var tipoComodidad = tipoComodidadRepository.findById(dto.getIdTipoComodidad().getId())
			.orElseThrow(() -> new RuntimeException("Tipo de comodidad no encontrada"));
		
		var jpa = ComodidadAlojamiento.builder()
				.codigoComodidad(dto.getCodigoComodidad())
				.txtNombre(dto.getTxtNombre())
				.txtDescripcion(dto.getTxtDescripcion())
				.idTipoComodidad(tipoComodidad)
				.build();
		
		this.comodidadAlojamientoRepository.save(jpa);
		
		return GenericAPIMessageDTO.builder()
				.mensaje("¡Comodidad creada con éxito!")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
	}
	
	@Transactional
	public GenericAPIMessageDTO modificarComodidad(ComodidadAlojamientoDTO dto) {
		
		var tipoComodidad = tipoComodidadRepository.findById(dto.getIdTipoComodidad().getId())
			.orElseThrow(() -> new RuntimeException("Tipo de comodidad no encontrada"));
		
		var jpa = this.comodidadAlojamientoRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("Comodidad no encontrada"));

		jpa.setIconoComodidad(dto.getIconoComodidad());
		jpa.setTxtDescripcion(dto.getTxtDescripcion());
		jpa.setTxtNombre(dto.getTxtNombre());
		jpa.setIdTipoComodidad(tipoComodidad);
		jpa.setCodigoComodidad(dto.getCodigoComodidad());
		
		this.comodidadAlojamientoRepository.save(jpa);
		
		return GenericAPIMessageDTO.builder()
				.mensaje("¡Comodidad modificada con éxito!")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
	}
	
	@Transactional
	public GenericAPIMessageDTO eliminarComodidad(String codigo) {
		var comodidad = this.buscarComodidadAlojamientoPorCod(codigo);
		this.comodidadAlojamientoRepository.deleteById(comodidad.getId());
		return GenericAPIMessageDTO.builder()
				.mensaje("¡Comodidad eliminada con éxito!")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
	}
	
	public ComodidadAlojamientoDTO buscarComodidadAlojamientoPorCod(String codigo) {
		
		var jpa = this.comodidadAlojamientoRepository.findByCodigoComodidad(codigo)
				.orElseThrow(() -> new RuntimeException("La comodidad con este código " + codigo + " no existe."));
		
		return ComodidadAlojamientoDTO.builder()
				.codigoComodidad(jpa.getCodigoComodidad())
				.iconoComodidad(jpa.getIconoComodidad())
				.id(jpa.getId())
				.idTipoComodidad(TipoComodidadDTO.builder().
						id(jpa.getIdTipoComodidad().getId())
						.txtNombre(jpa.getIdTipoComodidad().getTxtNombre())
						.codigoTipoComodidad(jpa.getIdTipoComodidad().getCodigoTipoComodidad()).build())
				.txtDescripcion(jpa.getTxtDescripcion())
				.txtNombre(jpa.getTxtNombre())
				.build();
	}
	
	public List<ComodidadAlojamientoDTO> findAll() {
		var jpaList = this.comodidadAlojamientoRepository.findAll();
		var dtoList = new ArrayList<ComodidadAlojamientoDTO>();
		if(!jpaList.isEmpty()) {
			jpaList.forEach(jpa -> {
				var dto = ComodidadAlojamientoDTO.builder()
						.codigoComodidad(jpa.getCodigoComodidad())
						.iconoComodidad(jpa.getIconoComodidad())
						.id(jpa.getId())
						.idTipoComodidad(TipoComodidadDTO.builder().
								id(jpa.getIdTipoComodidad().getId())
								.txtNombre(jpa.getIdTipoComodidad().getTxtNombre())
								.codigoTipoComodidad(jpa.getIdTipoComodidad().getCodigoTipoComodidad()).build())
						.txtDescripcion(jpa.getTxtDescripcion())
						.txtNombre(jpa.getTxtNombre())
						.build();
				dtoList.add(dto);
			});
		}
		return dtoList;
	}
}
