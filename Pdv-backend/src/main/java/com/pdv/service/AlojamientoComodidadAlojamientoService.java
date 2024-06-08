package com.pdv.service;

import org.springframework.stereotype.Service;

import com.pdv.dto.AlojamientoComodidadAlojamientoDTO;
import com.pdv.dto.ComodidadAlojamientoDTO;
import com.pdv.dto.TipoComodidadDTO;
import com.pdv.model.AlojamientoComodidadAlojamiento;
import com.pdv.model.ComodidadAlojamiento;

@Service
public class AlojamientoComodidadAlojamientoService {
	
	/**
	 * Método que sirve para pasar un AlojamientoComodidadAlojamiento
	 * a su versión en DTO con los datos necesarios para su gestión.
	 * @param jpa
	 * @return
	 */
	AlojamientoComodidadAlojamientoDTO toDtoWithoutAlojamiento(AlojamientoComodidadAlojamiento jpa){
		var dto = AlojamientoComodidadAlojamientoDTO.builder()
				.id(jpa.getId())
				.idComodidadAlojamiento(ComodidadAlojamientoDTO.builder()
						.codigoComodidad(jpa.getIdComodidadAlojamiento().getCodigoComodidad())
						.iconoComodidad(jpa.getIdComodidadAlojamiento().getIconoComodidad())
						.id(jpa.getIdComodidadAlojamiento().getId())
						.txtDescripcion(jpa.getIdComodidadAlojamiento().getTxtDescripcion())
						.txtNombre(jpa.getIdComodidadAlojamiento().getTxtNombre())
						.idTipoComodidad(TipoComodidadDTO.builder()
								.id(jpa.getIdComodidadAlojamiento().getIdTipoComodidad().getId())
								.txtNombre(jpa.getIdComodidadAlojamiento().getIdTipoComodidad().getTxtNombre())
								.codigoTipoComodidad(jpa.getIdComodidadAlojamiento().getIdTipoComodidad().getCodigoTipoComodidad())
								.build())
						.build())
				.build();
		return dto;
	}
}
