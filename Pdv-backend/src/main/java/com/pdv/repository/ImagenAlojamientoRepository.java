package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.ImagenAlojamiento;

@Repository
public interface ImagenAlojamientoRepository extends JpaRepository<ImagenAlojamiento, Long> {

}
