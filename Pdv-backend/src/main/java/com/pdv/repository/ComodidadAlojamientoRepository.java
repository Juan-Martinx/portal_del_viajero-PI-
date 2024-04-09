package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.ComodidadAlojamiento;

@Repository
public interface ComodidadAlojamientoRepository extends JpaRepository<ComodidadAlojamiento, Long> {

}
