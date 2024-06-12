package com.pdv.oauth.dto;

import java.util.List;

public record AppUsuarioDTO (
	    String username,
	    String password,
	    String txtDni,
	    Integer numTelefono,
	    String txtEmail,
	    List<String> perfiles){}