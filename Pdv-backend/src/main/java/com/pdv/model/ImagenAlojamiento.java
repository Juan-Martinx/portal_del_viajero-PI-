package com.pdv.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Imagen_alojamiento")
public class ImagenAlojamiento {
	
	//Relacion con alojamiento falta
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen_alojamiento")
	private Long id;
	
	@Column(name = "datos_imagen", nullable = false)
	@Lob
	private Byte[] datosImagen;
	
	@Column(name = "num_orden", nullable = false)
	private Integer numOrden;

}
