package com.pdv.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pdv.dto.ComodidadAlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.TipoComodidadDTO;
import com.pdv.model.ComodidadAlojamiento;
import com.pdv.repository.AlojamientoComodidadAlojamientoRepository;
import com.pdv.repository.ComodidadAlojamientoRepository;
import com.pdv.repository.TipoComodidadRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComodidadAlojamientoService {
	
	private final ComodidadAlojamientoRepository comodidadAlojamientoRepository;
	private final AlojamientoComodidadAlojamientoRepository alojamientoComodidadAlojamientoRepository;
	private final TipoComodidadRepository tipoComodidadRepository;
	
	/**
	 * Método que sirve para crear una comodidad.
	 * @param dto
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO crearComodidad(ComodidadAlojamientoDTO dto) {
		
		var tipoComodidad = tipoComodidadRepository.findById(dto.getIdTipoComodidad().getId())
			.orElseThrow(() -> new RuntimeException("Tipo de comodidad no encontrada"));
		
		var jpa = ComodidadAlojamiento.builder()
				.iconoComodidad(dto.getIconoComodidad())
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
	
	/**
	 * Método que sirve para modificar una comodidad
	 * @param dto
	 * @return
	 */
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
	
	/**
	 * Método que sirve para eliminar una comodidad.
	 * @param codigo
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO eliminarComodidad(String codigo) {
		var comodidad = this.buscarComodidadAlojamientoPorCod(codigo);
		this.alojamientoComodidadAlojamientoRepository.deleteByIdComodidad(comodidad.getId());
		this.comodidadAlojamientoRepository.deleteById(comodidad.getId());
		return GenericAPIMessageDTO.builder()
				.mensaje("¡Comodidad eliminada con éxito!")
				.estado(HttpStatus.OK)
				.fechaYHora(LocalDateTime.now())
				.build();
	}
	
	/**
	 * Método que busca comodidades según un código dado.
	 * @param codigo
	 * @return
	 */
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
	
	/**
	 * Método que encuentra comodidades desde el buscador
	 * del administrador del sistema.
	 * @param txtNombre
	 * @param codigoComodidad
	 * @param tipoComodidadId
	 * @param codigoTipoComodidad
	 * @param page
	 * @return
	 */
	public List<ComodidadAlojamientoDTO> findComodidadesFromBuscador(String txtNombre, String codigoComodidad, Integer tipoComodidadId, String codigoTipoComodidad, Pageable page) {
		var jpaList = this.comodidadAlojamientoRepository.findComodidadesFromBuscador(txtNombre, codigoComodidad, tipoComodidadId, codigoTipoComodidad, page);
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
