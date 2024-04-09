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
@Table(name = "comodidad_alojamiento")
public class ComodidadAlojamiento {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comodidad_alojamiento")
	private Long id;
	
	@Column(name = "codigo_comodidad", nullable = false, unique = true, length = 50)
	private String codigoComodidad;
	
	@Column(name = "txt_nombre", nullable = false, length = 50)
	private String txtNombre;
	
	@Column(name = "txt_descripcion", nullable = false)
	private String txtDescripcion;
	
	@ManyToOne(optional = false, targetEntity = TipoComodidad.class)
	@JoinColumn(referencedColumnName = "id_tipo_comodidad", columnDefinition = "id_tipo_comodidad", nullable = false)
	private TipoComodidad tipoComodidad;

	@OneToMany(mappedBy = "comodidadAlojamiento", targetEntity = AlojamientoComodidadAlojamiento.class)
	private Set<AlojamientoComodidadAlojamiento> alojamientoComodidades;
}
