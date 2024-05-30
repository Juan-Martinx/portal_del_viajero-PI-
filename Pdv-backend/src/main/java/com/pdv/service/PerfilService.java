package com.pdv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pdv.dto.PerfilDTO;
import com.pdv.model.Usuario;

@Service
public class PerfilService {

	public List<String> getPerfilesFromUserJpa(Usuario usuario){
		var perfiles = new ArrayList<String>();
		usuario.getIdPerfiles().forEach(perfil -> perfiles.add(perfil.getCodPerfil().name()));
		return perfiles;
	}
	
}
