package com.pdv.oauth.commons;

/**
 * Clase que contiene los paths de la aplicación oauth.
 */
public class PathCommons {
    
	/*Path General del Backend*/
	public static final String BACKEND = "/oauth2";
	
	/*USUARIOS*/
	public static final String AUTH = BACKEND + "/auth";
	
	/*LOGIN ENTRYPOINT*/
	public static final String LOGIN_ENTRYPOINT = BACKEND + "/user-actions";
	
	/*CLIENT DE AUTENTIFICACIÓN*/
	public static final String AUTENTIFICATION_CLIENT = BACKEND + "/client";

}
