package com.pdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pdv.model.ValoracionAlojamiento;

import jakarta.transaction.Transactional;

@Repository
public interface ValoracionAlojamientoRepository extends JpaRepository<ValoracionAlojamiento, Long>{
	public Optional<List<ValoracionAlojamiento>> findByIdUsuarioIdAndIdAlojamientoId(Long idUsuario, Long idAlojamiento);
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM valoracion_alojamiento WHERE id_usuario = :id", nativeQuery = true)
	public void deleteByIdUsuario(Long id);
	
}
