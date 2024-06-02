package com.pdv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alojamiento_dias_ocupados")
public class AlojamientoDiasOcupados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alojamiento_dias_ocupados")
	private Long id;
	
	@Column(name = "dia", nullable = false)
	private Integer dia;
	
	@Column(name = "mes", nullable = false)
	private Integer mes;
	
	@Column(name = "anyo", nullable = false)
	private Integer anyo;
	
	@ManyToOne(targetEntity = Alojamiento.class)
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento")
	private Alojamiento idAlojamiento;

}
