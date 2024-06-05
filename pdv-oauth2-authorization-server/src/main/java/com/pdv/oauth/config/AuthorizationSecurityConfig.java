package com.pdv.oauth.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
import com.pdv.oauth.model.Usuario;
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
                new LoginUrlAuthenticationEntryPoint("/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
        )).oauth2ResourceServer(resource -> resource.jwt(Customizer.withDefaults()));

		return http.build();
	}
	
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
                                .requestMatchers(PathCommons.AUTH + "/**", PathCommons.AUTENTIFICATION_CLIENT + "/**", "/login").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(login -> login.loginPage("/login"))
                .oauth2Login(login -> login.loginPage("/login")
                        .successHandler(authenticationSuccessHandler())
                )
                .apply(federatedIdentityConfigurer);
        http.logout(logout ->
        		logout.logoutSuccessUrl("http://127.0.0.1:4200/logout"));
		return http.build();
	}
    
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
    
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }
    
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder().issuer(PathCommons.API_URL).build();
    }
    
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource){
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
    
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRSAKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }
    
    private RSAKey generateRSAKey() {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

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
    
    private AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new FederatedIdentityAuthenticationSuccessHandler();
    }
}