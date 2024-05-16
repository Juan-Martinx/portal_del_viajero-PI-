package com.pdv.oauth.dto;

import java.util.List;

public record AppUsuarioDTO (
	    String username,
	    String password,
	    List<String> perfiles){}