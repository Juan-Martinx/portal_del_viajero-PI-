package com.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.TipoComodidad;

@Repository
public interface TipoComodidadRepository extends JpaRepository<TipoComodidad, Long> {

}
