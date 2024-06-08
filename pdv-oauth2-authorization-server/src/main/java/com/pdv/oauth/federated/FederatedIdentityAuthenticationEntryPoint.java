package com.pdv.oauth.federated;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
/**
 * Punto de entrada de autenticación para identidades federadas.
 * <p>
 * Esta clase implementa la interfaz {@link AuthenticationEntryPoint} y se encarga de manejar las solicitudes de
 * autenticación para identidades federadas. Cuando se recibe una solicitud de autenticación para un proveedor de
 * identidad federada, esta clase redirige al usuario a la URI de autorización adecuada para iniciar el proceso de
 * autenticación federada.
 * </p>
 */
public final class FederatedIdentityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private String authorizationRequestUri = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
            + "/{registrationId}";

    private final AuthenticationEntryPoint delegate;

    private final ClientRegistrationRepository clientRegistrationRepository;
    
    /**
     * Constructor de la clase.
     *
     * @param loginPageUrl                 URL de la página de inicio de sesión.
     * @param clientRegistrationRepository repositorio de registro de cliente OAuth2.
     */
    public FederatedIdentityAuthenticationEntryPoint(String loginPageUrl, ClientRegistrationRepository clientRegistrationRepository) {
        this.delegate = new LoginUrlAuthenticationEntryPoint(loginPageUrl);
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    /**
     * Método para manejar la solicitud de autenticación.
     *
     * @param request                 solicitud HTTP recibida.
     * @param response                respuesta HTTP que se enviará.
     * @param authenticationException excepción de autenticación, si la hubiera.
     * @throws IOException      si ocurre un error de entrada/salida durante el proceso.
     * @throws ServletException si ocurre un error de servlet durante el proceso.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        String idp = request.getParameter("idp");
        if (idp != null) {
            ClientRegistration clientRegistration = this.clientRegistrationRepository.findByRegistrationId(idp);
            if (clientRegistration != null) {
                String redirectUri = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
                        .replaceQuery(null)
                        .replacePath(this.authorizationRequestUri)
                        .buildAndExpand(clientRegistration.getRegistrationId())
                        .toUriString();
                this.redirectStrategy.sendRedirect(request, response, redirectUri);
                return;
            }
        }

        this.delegate.commence(request, response, authenticationException);
    }
    
    /**
     * Establece la URI de la solicitud de autorización.
     *
     * @param authorizationRequestUri URI de la solicitud de autorización.
     */
    public void setAuthorizationRequestUri(String authorizationRequestUri) {
        this.authorizationRequestUri = authorizationRequestUri;
    }

}