package com.pdv.model;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.pdv.enums.CodPerfiles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "perfil")
public class Perfil implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Long id;

	@Column(name = "cod_perfil", length = 50, unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private CodPerfiles codPerfil;

	@Column(name = "txt_perfil", nullable = false)
	private String txtPerfil;

	@ManyToMany(mappedBy = "idPerfiles", targetEntity = Usuario.class)
	private Set<Usuario> idUsuarios;

	@Override
	public String getAuthority() {
		return codPerfil.name();
	}
}