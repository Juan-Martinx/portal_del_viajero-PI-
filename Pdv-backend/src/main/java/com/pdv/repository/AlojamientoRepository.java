package com.pdv.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pdv.model.Alojamiento;

import jakarta.transaction.Transactional;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long> {
	
	public Optional<List<Alojamiento>> findByIdUsuarioId(Long id);
	
	public Optional<List<Alojamiento>> findByIdUsuarioUsername(String username);
	
    @Query(value =  
    		" SELECT a.id_alojamiento FROM alojamiento a "
    		+ " LEFT JOIN ubicacion_alojamiento ua ON ua.id_alojamiento = a.id_alojamiento "
    		+ " WHERE a.id_alojamiento NOT IN "
    			+ "	(SELECT ado.id_alojamiento "
    				+ "	FROM alojamiento_dias_ocupados ado "
    				+ "	WHERE ado.id_alojamiento = a.id_alojamiento "
    				+ " AND STR_TO_DATE(CONCAT(ado.dia, '-', ado.mes, '-', ado.anyo), '%d-%m-%Y') BETWEEN :fechaLlegada AND :fechaSalida "
    				+ " ) "
    		+ " AND ( a.num_precio_noche BETWEEN :numPrecioNocheMin AND :numPrecioNocheMax ) "
    		+ " AND ( :provincia = '' OR ua.provincia LIKE %:provincia% ) "
    		+ " AND ( -1 IN :idComodidades OR :numComodidades = "
    			+ "	( SELECT COUNT(aca.id_alojamiento_comodidad_alojamiento) FROM alojamiento_comodidad_alojamiento aca "
    				+ "	WHERE aca.id_alojamiento = a.id_alojamiento "
    				+ " AND aca.id_comodidad_alojamiento IN :idComodidades "
    			+ " ) "
    		+ " ) ", nativeQuery = true)
	public List<Long> findWithFilters(String provincia,Double numPrecioNocheMin, Double numPrecioNocheMax , LocalDate fechaLlegada, LocalDate fechaSalida, List<Long> idComodidades, Integer numComodidades, Pageable page);

}
