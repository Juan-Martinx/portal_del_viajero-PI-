package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.UbicacionAlojamiento;

@Repository
public interface UbicacionAlojamientoRepository extends JpaRepository<UbicacionAlojamiento, Long> {

}
