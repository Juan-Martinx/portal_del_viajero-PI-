package com.pdv.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pdv.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}