package com.pdv.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "usuario")
public class Usuario implements UserDetails{

	private static final long serialVersionUID = -2996297027505667791L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "txt_nombre_usuario", length = 15, nullable = false, unique = true)
	private String username;

	@Column(name = "txt_descripcion")
	private String txtDescripcion;

	@Column(name = "txt_dni", length = 9, nullable = false, unique = true)
	private String txtDni;

	@Column(name = "num_telefono", nullable = false, unique = true)
	private Integer numTelefono;

	@Column(name = "txt_email", nullable = false, unique = true)
	private String txtEmail;

	@Column(name = "txt_password", nullable = false, length = 20)
	private String password;
	
	@Column(name = "datos_imagen_usuario")
	@Basic(fetch = FetchType.LAZY)
	@Lob
	private Byte[] datosImagenUsuario;
	
	private boolean expired = false;
	private boolean locked = false;
	private boolean credentialsExpired = false;
	private boolean disabled = false;
	
	@ManyToMany(targetEntity = Perfil.class)
	@JoinTable(name = "usuario_perfiles", joinColumns = @JoinColumn(name = "id_usuario"), 
		inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private Set<Perfil> idPerfiles;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = AlquilerAlojamiento.class)
	private Set<AlquilerAlojamiento> idAlquileres;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = Alojamiento.class)
	private Set<Alojamiento> idAlojamiento;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = ValoracionAlojamiento.class)
	private Set<ValoracionAlojamiento> idValoracionesAlojamientos;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.idPerfiles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return !this.disabled;
	}

}