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
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Comodidad_alojamiento")
public class ComodidadAlojamiento {
	
	//Relacion con alojamiento falta
	
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
	
	@ManyToOne(optional = false)
	@JoinColumn(referencedColumnName = "id_tipo_comodidad", columnDefinition = "id_tipo_comodidad")
	private TipoComodidad tipoComodidad;

}
