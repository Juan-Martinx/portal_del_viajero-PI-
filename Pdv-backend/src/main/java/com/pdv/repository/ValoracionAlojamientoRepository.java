package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.ValoracionAlojamiento;

@Repository
public interface ValoracionAlojamientoRepository extends JpaRepository<ValoracionAlojamiento, Long>{

}
