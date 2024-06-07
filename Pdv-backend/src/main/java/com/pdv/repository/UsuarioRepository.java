package com.pdv.repository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pdv.model.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsername(String username);
	
	@Query(" SELECT u FROM Usuario u "
			+ " WHERE ( :username = '' OR u.username LIKE %:username% ) "
			+ " AND ( :txtDni = '' OR u.txtDni LIKE %:txtDni% ) "
			+ " AND ( :txtEmail = '' OR u.txtEmail LIKE %:txtEmail% ) ")
	public Page<Usuario> findUsuariosFromBuscador(String username, String txtDni, String txtEmail, Pageable page);
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM usuario_perfiles WHERE id_usuario = :id", nativeQuery = true)
	public void deleteAllPerfilesRelationshipByIdUsuario(Long id);
}