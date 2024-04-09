package com.pdv.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
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
	
	@Column(name = "num_precio_plaza", nullable = false)
	private Double numPrecioPlaza;
	
	@Column(name = "num_camas", nullable = false)
	private Integer numCamas;
	
	@Column(name = "num_banyos", nullable = false)
	private Integer numbanyos;
	
	@ManyToOne(optional = false, targetEntity = Usuario.class)
	@JoinColumn(referencedColumnName = "id_usuario", columnDefinition = "id_usuario")
	private Usuario gestorAlojamiento;
	
	@OneToMany(mappedBy = "alojamientoValorado", targetEntity = ValoracionAlojamiento.class)
	private Set<ValoracionAlojamiento> valoracionesAlojamiento;
	
	@OneToMany(mappedBy = "alojamientoAlquilado", targetEntity = AlquilerAlojamiento.class)
	private Set<AlquilerAlojamiento> alquileresAlojamiento;
}
