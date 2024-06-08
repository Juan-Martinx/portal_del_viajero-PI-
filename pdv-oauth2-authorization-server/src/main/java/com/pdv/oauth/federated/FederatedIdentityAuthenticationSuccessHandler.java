package com.pdv.oauth.federated;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Manejador de éxito de autenticación para identidades federadas.
 * <p>
 * Esta clase implementa la interfaz {@link AuthenticationSuccessHandler} y se encarga de manejar las solicitudes
 * de autenticación exitosas para identidades federadas. Después de una autenticación exitosa, se llama a los
 * consumidores asociados para procesar los usuarios autenticados, y luego se delega al manejador predeterminado
 * de Spring Security para continuar el flujo de autenticación.
 * </p>
 */
public final class FederatedIdentityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // Delegado para el manejo del éxito de autenticación
    private final AuthenticationSuccessHandler delegate = new SavedRequestAwareAuthenticationSuccessHandler();

    // Consumidores para manejar los usuarios OAuth2 y OIDC autenticados
    private Consumer<OAuth2User> oauth2UserHandler = (user) -> {};
    private Consumer<OidcUser> oidcUserHandler = (user) -> this.oauth2UserHandler.accept(user);

    /**
     * Método para manejar el éxito de autenticación.
     *
     * @param request        solicitud HTTP recibida.
     * @param response       respuesta HTTP que se enviará.
     * @param authentication objeto de autenticación que representa la autenticación exitosa.
     * @throws IOException      si ocurre un error de entrada/salida durante el proceso.
     * @throws ServletException si ocurre un error de servlet durante el proceso.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            if (authentication.getPrincipal() instanceof OidcUser) {
                this.oidcUserHandler.accept((OidcUser) authentication.getPrincipal());
            } else if (authentication.getPrincipal() instanceof OAuth2User) {
                this.oauth2UserHandler.accept((OAuth2User) authentication.getPrincipal());
            }
        }

        this.delegate.onAuthenticationSuccess(request, response, authentication);
    }
    
    /**
     * Establece el consumidor para manejar usuarios OAuth2 autenticados.
     *
     * @param oauth2UserHandler consumidor para manejar usuarios OAuth2.
     */
    public void setOAuth2UserHandler(Consumer<OAuth2User> oauth2UserHandler) {
        this.oauth2UserHandler = oauth2UserHandler;
    }

    /**
     * Establece el consumidor para manejar usuarios OIDC autenticados.
     *
     * @param oidcUserHandler consumidor para manejar usuarios OIDC.
     */
    public void setOidcUserHandler(Consumer<OidcUser> oidcUserHandler) {
        this.oidcUserHandler = oidcUserHandler;
    }

}