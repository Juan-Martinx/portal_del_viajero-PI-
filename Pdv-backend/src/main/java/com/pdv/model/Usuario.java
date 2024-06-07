package com.pdv.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "username", length = 15, nullable = false, unique = true)
	private String username;

	@Column(name = "txt_descripcion")
	private String txtDescripcion;

	@Column(name = "txt_dni", length = 9, unique = true)
	private String txtDni;

	@Column(name = "num_telefono", unique = true)
	private Integer numTelefono;

	@Column(name = "txt_email", unique = true)
	private String txtEmail;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "url_imagen_usuario")
	private String urlImagenUsuario;
	
	private boolean expired = false;
	private boolean locked = false;
	private boolean credentialsExpired = false;
	private boolean disabled = false;
	
	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private GoogleUser googleAccount;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_perfiles", joinColumns = @JoinColumn(name = "id_usuario"), 
		inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private Set<Perfil> idPerfiles;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = AlquilerAlojamiento.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<AlquilerAlojamiento> idAlquileres;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = Alojamiento.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<Alojamiento> idAlojamiento;
	
	@OneToMany(mappedBy = "idUsuario", targetEntity = ValoracionAlojamiento.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
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