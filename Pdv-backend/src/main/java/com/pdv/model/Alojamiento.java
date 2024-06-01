package com.pdv.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "alojamiento")
public class Alojamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alojamiento")
	private Long id;
	
	@Column(name = "txt_nombre", length = 50, nullable = false)
	private String txtNombre;
	
	@Column(name = "txt_descripcion", nullable = false)
	private String txtDescripcion;
	
	@Column(name = "num_plaza_min", nullable = false)
	private Integer numPlazaMin;
	
	@Column(name = "num_plaza_max", nullable = false)
	private Integer numPlazaMax;
	
	@Column(name = "num_precio_noche", nullable = false)
	private Double numPrecioNoche;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(referencedColumnName = "id_usuario", name = "id_usuario", nullable = false)
	private Usuario idUsuario;
	
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = ValoracionAlojamiento.class)
	private Set<ValoracionAlojamiento> idValoracionesAlojamiento;
	
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = AlquilerAlojamiento.class)
	private Set<AlquilerAlojamiento> idAlquileresAlojamiento;
	
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = ImagenAlojamiento.class)
	private Set<ImagenAlojamiento> idImagenesAlojamiento;
	
	@OneToOne(mappedBy = "idAlojamiento", targetEntity = UbicacionAlojamiento.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private UbicacionAlojamiento idUbicacion;
	
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = AlojamientoComodidadAlojamiento.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<AlojamientoComodidadAlojamiento> idAlojamientoComodidades;
}
