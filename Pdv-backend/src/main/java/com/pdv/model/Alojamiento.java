package com.pdv.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alojamiento")
@EqualsAndHashCode(exclude = "idUsuario")
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
	
	//Los CascadeType.ALL son debido a que todo lo que le ocurra al alojamiento le ocurrirá también a sus clases hijas
	@JsonManagedReference
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = ValoracionAlojamiento.class, cascade = CascadeType.ALL)
	private Set<ValoracionAlojamiento> idValoracionesAlojamiento;
	
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = AlquilerAlojamiento.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<AlquilerAlojamiento> idAlquileresAlojamiento;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = ImagenAlojamiento.class, cascade = CascadeType.ALL)
	private Set<ImagenAlojamiento> idImagenesAlojamiento;
	
	@OneToOne(mappedBy = "idAlojamiento", targetEntity = UbicacionAlojamiento.class, cascade = CascadeType.ALL)
	private UbicacionAlojamiento idUbicacion;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = AlojamientoComodidadAlojamiento.class, cascade = CascadeType.ALL)
	private Set<AlojamientoComodidadAlojamiento> idAlojamientoComodidades;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "idAlojamiento", targetEntity = AlojamientoDiasOcupados.class, cascade = CascadeType.ALL)
	private Set<AlojamientoDiasOcupados> idAlojamientoDiasOcupados;
}
