package com.pdv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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
@Table(name = "ubicacion_alojamiento")
@EqualsAndHashCode(exclude = "idAlojamiento")
public class UbicacionAlojamiento {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ubicacion_alojamiento")
	private Long id;
	
	@Column(name = "codigo_postal", nullable = false)
	private Integer codigoPostal;
	
	@Column(name = "ciudad", length = 60)
	private String ciudad;
	
	@Column(name = "provincia", nullable = false, length = 60)
	private String provincia;
	
	@Column(name = "linea_direccion", nullable = false)
	private String lineaDireccion;
	
	@Column(name = "longitud")
	private String longitud;
	
	@Column(name = "latitud")
	private String latitud;
	
	@OneToOne(targetEntity = Alojamiento.class)
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento", nullable = false)
	private Alojamiento idAlojamiento;

}
