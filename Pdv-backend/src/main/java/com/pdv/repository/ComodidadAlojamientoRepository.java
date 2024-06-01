package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pdv.model.ComodidadAlojamiento;
import com.pdv.model.Usuario;

@Repository
public interface ComodidadAlojamientoRepository extends JpaRepository<ComodidadAlojamiento, Long> {
	
	public Optional<ComodidadAlojamiento> findByCodigoComodidad(String codigoComodidad);
	
	@Query(" SELECT ca FROM ComodidadAlojamiento ca "
			+ " WHERE ( :txtNombre = '' OR ca.txtNombre LIKE %:txtNombre% ) "
			+ " AND ( :codigoComodidad = '' OR ca.codigoComodidad LIKE %:codigoComodidad% ) "
			+ " AND ( :tipoComodidadId = -1 OR ca.idTipoComodidad.id = :tipoComodidadId ) ")
	public Page<ComodidadAlojamiento> findComodidadesFromBuscador(String txtNombre, String codigoComodidad, Integer tipoComodidadId, Pageable page);
}
