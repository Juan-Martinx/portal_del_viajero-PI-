package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.ComodidadAlojamiento;

@Repository
public interface ComodidadAlojamientoRepository extends JpaRepository<ComodidadAlojamiento, Long> {
	
	public Optional<ComodidadAlojamiento> findByCodigoComodidad(String codigoComodidad);
	
}
