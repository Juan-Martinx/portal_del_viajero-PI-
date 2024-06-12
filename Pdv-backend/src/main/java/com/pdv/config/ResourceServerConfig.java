package com.pdv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import pdv.commons.PathCommons;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    /**
     * [CONFIGURATION BEAN]
     * Bean que establece el filtro de seguridad del servidor de recursos,
     * espera que le llegue un JWT y lo decodifica usando las configuraciones
     * aportadas por el servidor de seguridad al que está enlazado.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    	http.cors(Customizer.withDefaults());

        return http
                .authorizeHttpRequests(auth -> auth.requestMatchers(PathCommons.USUARIOS + "/public/**", PathCommons.ALOJAMIENTO + "/public/**"
                		, PathCommons.MEDIA_RESOURCES + "/public/**", PathCommons.COMODIDAD_ALOJAMIENTO + "/public/**").permitAll()
                		.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))))
                .build();
    }

    /**
     * [CONFIGURATION BEAN]
     * Bean de configuración que sirve para devolver el tipo de autoridad dentro
     * del servidor de recursos. De esta manera, podemos asegurarnos de que los usuarios
     * puedan hacer o no ciertas peticiones.
     * @return
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("perfiles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}