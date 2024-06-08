package com.pdv.oauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class BeansConfig {
	
    @Value("${frontend.api}")
    private String frontendAPI;
    
	/**
	 * [CONFIGURATION BEAN]
	 * Bean que sirve para encriptar contraseñas
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * [CONFIGURATION BEAN]
	 * Bean que sirve para configurar la política
	 * de CORS que funcionará en toda la aplicación.
	 * @return
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration cors = new CorsConfiguration();
		cors.addAllowedHeader("*");
		cors.addAllowedMethod("*");
		cors.setAllowCredentials(true);
		cors.addAllowedOrigin(frontendAPI);
		source.registerCorsConfiguration("/**", cors);
		return source;
	}
}
