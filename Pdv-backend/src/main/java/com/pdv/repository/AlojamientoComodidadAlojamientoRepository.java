package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.AlojamientoComodidadAlojamiento;

@Repository
public interface AlojamientoComodidadAlojamientoRepository extends JpaRepository<AlojamientoComodidadAlojamiento, Long> {

	Optional<AlojamientoComodidadAlojamiento> findByIdAlojamientoIdAndIdComodidadAlojamientoId(Long idAlojamiento, Long idComodidad);
}
