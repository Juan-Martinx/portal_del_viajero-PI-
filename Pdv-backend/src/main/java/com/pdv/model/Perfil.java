package com.pdv.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "perfil")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Long id;

	@Column(name = "cod_perfil", length = 50, unique = true, nullable = false)
	private String codPerfil;

	@Column(name = "txt_perfil", nullable = false)
	private String txtPerfil;

	@OneToMany(mappedBy = "idPerfil", targetEntity = Usuario.class)
	private Set<Usuario> idUsuarios;
}