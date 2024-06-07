package com.pdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdv.model.AlquilerAlojamiento;

import jakarta.transaction.Transactional;

@Repository
public interface AlquilerAlojamientoRepository extends JpaRepository<AlquilerAlojamiento, Long> {
	
	public List<AlquilerAlojamiento> findByIdUsuarioId(Long idUsuario);
	
	public List<AlquilerAlojamiento> findByIdAlojamientoIdUsuarioId(Long id);

	public Optional<List<AlquilerAlojamiento>> findByIdUsuarioIdAndIdAlojamientoId(Long idUsuario, Long idAlojamiento);

	@Modifying
	@Transactional
    @Query(value = "DELETE FROM alquiler_alojamiento WHERE id_alquiler_alojamiento = :id", nativeQuery = true)
	public void deleteById(Long id);
	
}
