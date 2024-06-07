package com.pdv.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@EqualsAndHashCode(exclude = {"idAlojamiento", "idUsuario"})
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
	
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(referencedColumnName = "id_usuario", name = "id_usuario", nullable = false)
	private Usuario idUsuario;
	
	@ManyToOne(targetEntity = Alojamiento.class, fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento", nullable = false)
	private Alojamiento idAlojamiento;
}
