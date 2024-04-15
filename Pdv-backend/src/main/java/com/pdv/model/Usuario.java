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
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "txt_nombre_usuario", length = 15, nullable = false, unique = true)
	private String txtNombreUsuario;

	@Column(name = "txt_descripcion")
	private String txtDescripcion;

	@Column(name = "txt_dni", length = 9, nullable = false, unique = true)
	private String txtDni;

	@Column(name = "num_telefono", nullable = false, unique = true)
	private Integer numTelefono;

	@Column(name = "txt_email", nullable = false, unique = true)
	private String txtEmail;

	@Column(name = "txt_password", nullable = false, length = 20)
	private String txtPassword;

	@ManyToOne(targetEntity = Perfil.class)
	@JoinColumn(referencedColumnName = "id_perfil", name = "id_perfil", nullable = false)
	private Perfil idPerfil;
	
	@OneToMany(mappedBy = "idUsuarioCliente", targetEntity = AlquilerAlojamiento.class)
	private Set<AlquilerAlojamiento> idAlquileres;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = Alojamiento.class)
	private Set<Alojamiento> idAlojamiento;
	
	@OneToMany(mappedBy = "idUsuarioValorador", targetEntity = ValoracionAlojamiento.class)
	private Set<ValoracionAlojamiento> idValoracionesAlojamientos;

}