package com.pdv.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Tipo_comodidad")
public class TipoComodidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_comodidad")
	private Long id;
	
	@Column(name = "codigo_tipo_comodidad", nullable = false, unique = true, length = 50)
	private String codigoTipoComodidad;
	
	@Column(name = "txt_nombre", nullable = false, length = 50)
	private String txtNombre;
	
	@OneToMany(mappedBy = "tipoComodidad")
	private Set<ComodidadAlojamiento> comodidadAlojamientos;

}
