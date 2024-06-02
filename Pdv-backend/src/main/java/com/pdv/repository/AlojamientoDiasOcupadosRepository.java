package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdv.model.AlojamientoDiasOcupados;

public interface AlojamientoDiasOcupadosRepository extends JpaRepository<AlojamientoDiasOcupados, Long>{
	
	public Optional<AlojamientoDiasOcupados> findByDiaAndMesAndAnyoAndIdAlojamientoId(Integer dia, Integer mes, Integer anyo, Long id);
}
