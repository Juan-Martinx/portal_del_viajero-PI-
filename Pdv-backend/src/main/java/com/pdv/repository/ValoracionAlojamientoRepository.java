package com.pdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.ValoracionAlojamiento;

@Repository
public interface ValoracionAlojamientoRepository extends JpaRepository<ValoracionAlojamiento, Long>{
	public Optional<List<ValoracionAlojamiento>> findByIdUsuarioIdAndIdAlojamientoId(Long idUsuario, Long idAlojamiento);
}
