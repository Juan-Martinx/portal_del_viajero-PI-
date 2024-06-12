package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pdv.model.AlojamientoDiasOcupados;

import jakarta.transaction.Transactional;

public interface AlojamientoDiasOcupadosRepository extends JpaRepository<AlojamientoDiasOcupados, Long>{
	
	public Optional<AlojamientoDiasOcupados> findByDiaAndMesAndAnyoAndIdAlojamientoId(Integer dia, Integer mes, Integer anyo, Long id);
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM alojamiento_dias_ocupados WHERE id_alojamiento_dias_ocupados = :id", nativeQuery = true)
	public void deleteById(Long id);
}
