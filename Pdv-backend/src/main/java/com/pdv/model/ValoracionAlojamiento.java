package com.pdv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "valoracion_alojamiento")
public class ValoracionAlojamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_valoracion_alojamiento")
	private Long id;
	
	@Column(name = "txt_mensaje", nullable = false)
	private String txtMensaje;
	
	@Column(name = "txt_asunto", nullable = false, length = 50)
	private String txtAsunto;
	
	
	@Column(name = "puntuacion", nullable = false)
	private Integer puntuacion;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(referencedColumnName = "id_usuario", name = "id_usuario", nullable = false)
	private Usuario idUsuarioValorador;
	
	@ManyToOne(targetEntity = Alojamiento.class)
	@JoinColumn(referencedColumnName = "id_alojamiento", name = "id_alojamiento", nullable = false)
	private Alojamiento idAlojamiento;
	
}
