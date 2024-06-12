package com.pdv.oauth.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.pdv.oauth.commons.PathCommons;
import com.pdv.oauth.enums.CodPerfiles;
import com.pdv.oauth.federated.FederatedIdentityAuthenticationSuccessHandler;
import com.pdv.oauth.federated.FederatedIdentityConfigurer;
import com.pdv.oauth.federated.UserRepositoryOAuth2UserHandler;
import com.pdv.oauth.repository.GoogleUserRepository;
import com.pdv.oauth.service.ClientService;
import com.pdv.oauth.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthorizationSecurityConfig {
	
	private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;
    private final GoogleUserRepository googleUserRepository;
    private final UsuarioService usuarioService;

    @Value("${oauth2.api}")
    private String oauth2API;
    
    @Value("${frontend.api}")
    private String frontendAPI;
    
    /**
     * Configura la cadena de filtros de seguridad para el servidor de autorización OAuth2.
     * <p>
     * Este método establece la configuración de seguridad específica para el servidor de autorización, incluyendo
     * la protección CSRF, el soporte para OAuth2 y OpenID Connect, y la gestión de excepciones de autenticación.
     * </p>
     * 
     * @param http el objeto {@link HttpSecurity} a configurar.
     * @return un {@link SecurityFilterChain} configurado.
     * @throws Exception si ocurre algún error durante la configuración de seguridad.
     */
	@Bean 
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors(Customizer.withDefaults());
		http.csrf((csrf) -> csrf.ignoringRequestMatchers(PathCommons.AUTH + "/**", PathCommons.AUTENTIFICATION_CLIENT + "/**"));
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
			.oidc(Customizer.withDefaults());
		
        http.oauth2ResourceServer(oAuthResourceServer -> oAuthResourceServer.jwt(Customizer.withDefaults()));

        http.exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint(PathCommons.LOGIN_ENTRYPOINT + "/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
        )).oauth2ResourceServer(resource -> resource.jwt(Customizer.withDefaults()));

		return http.build();
	}
	
	/**
	 * Configura la cadena de filtros de seguridad para las rutas web de la aplicación.
	 * <p>
	 * Este método establece la configuración de seguridad para la aplicación web, incluyendo el manejo de CORS,
	 * la protección CSRF, la configuración de login y logout, y la integración con proveedores de identidad federada.
	 * </p>
	 * 
	 * @param http el objeto {@link HttpSecurity} a configurar.
	 * @return un {@link SecurityFilterChain} configurado.
	 * @throws Exception si ocurre algún error durante la configuración de seguridad.
	 */
	@Bean
	@Order(2)
	public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors(Customizer.withDefaults());
		http.csrf((csrf) -> csrf.ignoringRequestMatchers(PathCommons.AUTH + "/**", PathCommons.AUTENTIFICATION_CLIENT + "/**"));
        FederatedIdentityConfigurer federatedIdentityConfigurer = new FederatedIdentityConfigurer()
                .oauth2UserHandler(new UserRepositoryOAuth2UserHandler(googleUserRepository, usuarioService));
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(PathCommons.AUTH + "/**", PathCommons.AUTENTIFICATION_CLIENT + "/**", PathCommons.LOGIN_ENTRYPOINT +"/login").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(login -> login.loginPage(PathCommons.LOGIN_ENTRYPOINT + "/login"))
                .oauth2Login(login -> login.loginPage(PathCommons.LOGIN_ENTRYPOINT + "/login")
                        .successHandler(authenticationSuccessHandler())
                )
                .apply(federatedIdentityConfigurer);
        http.logout(logout ->
        		logout.logoutSuccessUrl(frontendAPI + "/logout").logoutUrl(PathCommons.LOGIN_ENTRYPOINT + "/logout"));
		return http.build();
	}
    
	/**
	 * Personaliza los tokens OAuth2 emitidos por el servidor de autorización.
	 * <p>
	 * Este método configura un personalizador de tokens que agrega información adicional a los tokens JWT generados,
	 * como el tipo de token, los perfiles del usuario y el nombre de usuario. También elimina la reclamación de
	 * expiración para que el token no caduque.
	 * </p>
	 * 
	 * @return un {@link OAuth2TokenCustomizer} configurado para personalizar los tokens JWT.
	 */
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
		return context -> {
			Authentication principal = context.getPrincipal();
			if (context.getTokenType().getValue().equals("id_token")) {
				context.getClaims().claim("token_type", "id token");
			}
			if (context.getTokenType().getValue().equals("access_token")) {
				context.getClaims().claim("token_type", "access token");
				Set<String> perfiles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
						.collect(Collectors.toSet());
				if (perfiles.contains(CodPerfiles.OIDC_USER.name())) {
					var googUsr = this.googleUserRepository.findByEmail(principal.getName());
					if (googUsr.isPresent()) {
						var perfilesInternos = googUsr.get().getAppUser().getIdPerfiles();
						perfilesInternos.forEach(perfil -> {
							if (!perfiles.contains(perfil.getCodPerfil().name())) {
								perfiles.add(perfil.getCodPerfil().name());
							}
						});
					}

				}
				context.getClaims().claim("perfiles", perfiles).claim("username", principal.getName());
	            // Eliminar la reclamación exp - TOKEN NO CADUCA
	            context.getClaims().claims(claims -> claims.remove("exp"));
			}
		};
	}
    
	/**
	 * Crea y configura el registro de sesiones para la administración de sesiones de usuario.
	 * <p>
	 * Este método proporciona una instancia de {@link SessionRegistryImpl}, que se utiliza para gestionar y 
	 * rastrear las sesiones de los usuarios autenticados en la aplicación.
	 * </p>
	 * 
	 * @return una instancia de {@link SessionRegistry} para la administración de sesiones de usuario.
	 */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    
    /**
     * Crea y configura un publicador de eventos de sesión HTTP.
     * <p>
     * Este método proporciona una instancia de {@link HttpSessionEventPublisher}, que se utiliza para publicar 
     * eventos de creación y destrucción de sesiones HTTP. Esto es útil para la administración de sesiones 
     * concurrentes y para la integración con el registro de sesiones.
     * </p>
     * 
     * @return una instancia de {@link HttpSessionEventPublisher} para la publicación de eventos de sesión HTTP.
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
    /**
     * Crea y configura el servicio de autorización OAuth2.
     * <p>
     * Este método proporciona una instancia de {@link InMemoryOAuth2AuthorizationService}, que se utiliza para 
     * gestionar las autorizaciones OAuth2 en memoria. Este servicio almacena y recupera datos relacionados con 
     * las autorizaciones, como los tokens emitidos y las aprobaciones de consentimientos.
     * </p>
     * 
     * @return una instancia de {@link OAuth2AuthorizationService} para la gestión de autorizaciones OAuth2.
     */
    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }
    
    /**
     * Configura los ajustes del servidor de autorización OAuth2.
     * <p>
     * Este método proporciona una instancia de {@link AuthorizationServerSettings} configurada con el emisor (issuer)
     * especificado por la propiedad {@code oauth2API}. Estos ajustes se utilizan para definir propiedades del servidor
     * de autorización, como la URL del emisor.
     * </p>
     * 
     * @return una instancia de {@link AuthorizationServerSettings} con los ajustes configurados.
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder().issuer(oauth2API).build();
    }
    
    /**
     * Crea y configura un decodificador JWT para validar tokens JWT.
     * <p>
     * Este método proporciona una instancia de {@link JwtDecoder} configurada con la fuente de claves JWK especificada.
     * El decodificador JWT se utiliza para validar y decodificar tokens JWT emitidos por el servidor de autorización
     * OAuth2.
     * </p>
     * 
     * @param jwkSource la fuente de claves JWK utilizada para obtener las claves públicas necesarias para validar
     *                  los tokens JWT.
     * @return una instancia de {@link JwtDecoder} configurada para validar tokens JWT.
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource){
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
    
    /**
     * Crea y configura una fuente de claves JWK para la obtención de claves públicas.
     * <p>
     * Este método genera un par de claves RSA y crea un conjunto de claves JWK utilizando la clave pública generada.
     * La fuente de claves JWK se utiliza para proporcionar las claves públicas necesarias para validar los tokens JWT
     * emitidos por el servidor de autorización OAuth2.
     * </p>
     * 
     * @return una instancia de {@link JWKSource} configurada para proporcionar claves JWK.
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRSAKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }
    
    /**
     * Genera un par de claves RSA y construye un objeto RSAKey utilizando la clave pública y privada generadas.
     * <p>
     * Este método utiliza un generador de claves para crear un par de claves RSA, luego construye un objeto RSAKey
     * que representa estas claves utilizando la clave pública y privada generadas, asignándoles un identificador único.
     * </p>
     * 
     * @return un objeto {@link RSAKey} que representa el par de claves RSA generado.
     */
    private RSAKey generateRSAKey() {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

    /**
     * Genera un par de claves RSA.
     * <p>
     * Este método utiliza un generador de pares de claves RSA para crear un nuevo par de claves con una longitud de 2048 bits.
     * </p>
     * 
     * @return un objeto {@link KeyPair} que representa el par de claves RSA generado.
     * @throws RuntimeException si ocurre un error al intentar generar el par de claves RSA.
     */
    private KeyPair generateKeyPair() {
        KeyPair keyPair;
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        return keyPair;
    }
    
    /**
     * Crea y devuelve un manejador de éxito de autenticación.
     * <p>
     * Este método instancia un {@link FederatedIdentityAuthenticationSuccessHandler}, que se utiliza como manejador de éxito
     * de autenticación para procesar las solicitudes de autenticación exitosas, especialmente cuando se utilizan identidades
     * federadas como Google Sign-In.
     * </p>
     * 
     * @return un {@link AuthenticationSuccessHandler} configurado para manejar las solicitudes de autenticación exitosas.
     */
    private AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new FederatedIdentityAuthenticationSuccessHandler();
    }
}