package com.pdv.model;

import jakarta.persistence.CascadeType;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alojamiento_comodidad_alojamiento")
@EqualsAndHashCode(exclude="idAlojamiento")
public class AlojamientoComodidadAlojamiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alojamiento_comodidad_alojamiento")
	private Long id;
	
	@ManyToOne(targetEntity = ComodidadAlojamiento.class)
	@JoinColumn(referencedColumnName = "id_comodidad_alojamiento", name = "id_comodidad_alojamiento", nullable = false)
	private ComodidadAlojamiento idComodidadAlojamiento;
	
	@ManyToOne(targetEntity = Alojamiento.class)
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento")
	private Alojamiento idAlojamiento;

}
