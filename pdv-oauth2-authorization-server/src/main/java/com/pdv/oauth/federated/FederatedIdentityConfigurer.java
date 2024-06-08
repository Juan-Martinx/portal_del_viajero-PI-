package com.pdv.oauth.federated;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import com.pdv.oauth.commons.PathCommons;

import java.util.function.Consumer;

/**
 * Configurador para identidades federadas en la seguridad de Spring.
 * <p>
 * Esta clase extiende {@link AbstractHttpConfigurer} y proporciona métodos para configurar el manejo de identidades federadas
 * en la seguridad de Spring, incluyendo la URL de la página de inicio de sesión, la URI de la solicitud de autorización,
 * y los consumidores para manejar los usuarios OAuth2 y OIDC autenticados.
 * </p>
 */
public final class FederatedIdentityConfigurer extends AbstractHttpConfigurer<FederatedIdentityConfigurer, HttpSecurity> {

    // URL de la página de inicio de sesión, predeterminada a "/login"
    private String loginPageUrl = PathCommons.LOGIN_ENTRYPOINT + "/login";

    // URI de la solicitud de autorización, predeterminada a "/oauth2/authorization/{registrationId}"
    private String authorizationRequestUri;

    // Consumidor para manejar usuarios OAuth2 autenticados
    private Consumer<OAuth2User> oauth2UserHandler;

    // Consumidor para manejar usuarios OIDC autenticados
    private Consumer<OidcUser> oidcUserHandler;

    /**
     * Establece la URL de la página de inicio de sesión.
     *
     * @param loginPageUrl URL de la página de inicio de sesión.
     * @return Este configurador para configuración adicional.
     */
    public FederatedIdentityConfigurer loginPageUrl(String loginPageUrl) {
        Assert.hasText(loginPageUrl, "loginPageUrl cannot be empty");
        this.loginPageUrl = loginPageUrl;
        return this;
    }

    /**
     * Establece la URI de la solicitud de autorización.
     *
     * @param authorizationRequestUri URI de la solicitud de autorización.
     * @return Este configurador para configuración adicional.
     */
    public FederatedIdentityConfigurer authorizationRequestUri(String authorizationRequestUri) {
        Assert.hasText(authorizationRequestUri, "authorizationRequestUri cannot be empty");
        this.authorizationRequestUri = authorizationRequestUri;
        return this;
    }

    /**
     * Establece el consumidor para manejar usuarios OAuth2 autenticados.
     *
     * @param oauth2UserHandler Consumidor para manejar usuarios OAuth2.
     * @return Este configurador para configuración adicional.
     */
    public FederatedIdentityConfigurer oauth2UserHandler(Consumer<OAuth2User> oauth2UserHandler) {
        Assert.notNull(oauth2UserHandler, "oauth2UserHandler cannot be null");
        this.oauth2UserHandler = oauth2UserHandler;
        return this;
    }

    /**
     * Establece el consumidor para manejar usuarios OIDC autenticados.
     *
     * @param oidcUserHandler Consumidor para manejar usuarios OIDC.
     * @return Este configurador para configuración adicional.
     */
    public FederatedIdentityConfigurer oidcUserHandler(Consumer<OidcUser> oidcUserHandler) {
        Assert.notNull(oidcUserHandler, "oidcUserHandler cannot be null");
        this.oidcUserHandler = oidcUserHandler;
        return this;
    }
    
    // Inicializa la configuración de seguridad de Spring
    // @formatter:off
    @Override
    public void init(HttpSecurity http) throws Exception {
        // Obtiene el contexto de la aplicación para recuperar el repositorio de registro de clientes OAuth2
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        ClientRegistrationRepository clientRegistrationRepository =
                applicationContext.getBean(ClientRegistrationRepository.class);
        
        // Crea el punto de entrada de autenticación para identidades federadas
        FederatedIdentityAuthenticationEntryPoint authenticationEntryPoint =
                new FederatedIdentityAuthenticationEntryPoint(this.loginPageUrl, clientRegistrationRepository);
        if (this.authorizationRequestUri != null) {
            authenticationEntryPoint.setAuthorizationRequestUri(this.authorizationRequestUri);
        }

        // Crea el manejador de éxito de autenticación para identidades federadas
        FederatedIdentityAuthenticationSuccessHandler authenticationSuccessHandler =
                new FederatedIdentityAuthenticationSuccessHandler();
        if (this.oauth2UserHandler != null) {
            authenticationSuccessHandler.setOAuth2UserHandler(this.oauth2UserHandler);
        }
        if (this.oidcUserHandler != null) {
            authenticationSuccessHandler.setOidcUserHandler(this.oidcUserHandler);
        }

        http
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(authenticationEntryPoint)
                )
                .oauth2Login(oauth2Login -> {
                    oauth2Login.successHandler(authenticationSuccessHandler);
                    if (this.authorizationRequestUri != null) {
                        String baseUri = this.authorizationRequestUri.replace("/{registrationId}", "");
                        oauth2Login.authorizationEndpoint(authorizationEndpoint ->
                                authorizationEndpoint.baseUri(baseUri)
                        );
                    }
                });
    }
    // @formatter:on

}