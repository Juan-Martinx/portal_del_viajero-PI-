package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.Alojamiento;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long> {

}
