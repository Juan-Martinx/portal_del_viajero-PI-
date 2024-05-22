package com.pdv.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "imagen_alojamiento")
public class ImagenAlojamiento {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen_alojamiento")
	private Long id;
	
	@Column(name = "datos_imagen", nullable = false)
	@Basic(optional = false, fetch = FetchType.LAZY)
	@Lob
	private Byte[] datosImagen;
	
	@Column(name = "num_orden", nullable = false)
	private Integer numOrden;
	
	@ManyToOne(optional = false, targetEntity = Alojamiento.class)
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento", nullable = false)
	private Alojamiento idAlojamiento;

}
