package com.pdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.enums.CodPerfiles;
import com.pdv.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	Optional<Perfil> findByCodPerfil(CodPerfiles codPerfil);
}
