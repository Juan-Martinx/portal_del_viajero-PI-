package com.pdv.model;

import java.time.LocalDate;

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
@Table(name = "alquiler_alojamiento")
public class AlquilerAlojamiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alquiler_alojamiento")
	private Long id;
	
	@Column(name = "fecha_inicio_alquiler", nullable = false)
	private LocalDate fechaInicioAlquiler;
	
	@Column(name = "fecha_fin_alquiler", nullable = false)
	private LocalDate fechaFinAlquiler;
	
	@Column(name = "precio_total_alquiler", nullable = false)
	private Double precioTotalAlquiler;
	
	@Column(name = "num_plazas_reservadas", nullable = false)
	private Integer numPlazasReservadas;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(referencedColumnName = "id_usuario", name = "id_usuario", nullable = false)
	private Usuario idUsuario;
	
	@ManyToOne(targetEntity = Alojamiento.class)
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento", nullable = false)
	private Alojamiento idAlojamiento;
}
