package com.pdv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pdv.dto.TipoComodidadDTO;
import com.pdv.repository.TipoComodidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoComodidadService {
	
	private final TipoComodidadRepository tipoComodidadRepository;
	
	/**
	 * Devuelve una lista con todos los
	 * tipos de comodidad
	 * @return
	 */
	public List<TipoComodidadDTO> buscarTodosTiposComodidades(){
		var jpaList = tipoComodidadRepository.findAll();
		var dtoList = new ArrayList<TipoComodidadDTO>();
		if(!jpaList.isEmpty()){
			jpaList.forEach(jpa -> {
				var dto = TipoComodidadDTO.builder()
						.codigoTipoComodidad(jpa.getCodigoTipoComodidad())
						.id(jpa.getId())
						.txtNombre(jpa.getTxtNombre())
						.build();
				dtoList.add(dto);
			});
		}	
		return dtoList;
	}
}
