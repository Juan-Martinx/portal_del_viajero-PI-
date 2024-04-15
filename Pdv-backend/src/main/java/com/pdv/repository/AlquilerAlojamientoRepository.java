package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.AlquilerAlojamiento;

@Repository
public interface AlquilerAlojamientoRepository extends JpaRepository<AlquilerAlojamiento, Long> {

}
