package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pdv.model.AlojamientoComodidadAlojamiento;

import jakarta.transaction.Transactional;

@Repository
public interface AlojamientoComodidadAlojamientoRepository extends JpaRepository<AlojamientoComodidadAlojamiento, Long> {

	Optional<AlojamientoComodidadAlojamiento> findByIdAlojamientoIdAndIdComodidadAlojamientoId(Long idAlojamiento, Long idComodidad);
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM alojamiento_comodidad_alojamiento WHERE id_comodidad_alojamiento = :id", nativeQuery = true)
	public void deleteByIdComodidad(Long id);
}
