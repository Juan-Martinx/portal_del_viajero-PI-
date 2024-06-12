package com.pdv.oauth.federated;

import java.util.function.Consumer;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.pdv.oauth.model.GoogleUser;
import com.pdv.oauth.repository.GoogleUserRepository;
import com.pdv.oauth.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
/**
 * Manejador de usuario OAuth2 para el repositorio de usuarios de Google.
 * <p>
 * Esta clase implementa la interfaz {@link Consumer} y se encarga de manejar los usuarios OAuth2 autenticados,
 * generando un nuevo usuario en el repositorio de usuarios de Google si no existe, o simplemente registrando
 * el inicio de sesión si el usuario ya existe.
 * </p>
 */
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

    private final GoogleUserRepository googleUserRepository;
    private final UsuarioService usuarioService;
    
	@Override
	public void accept(OAuth2User user) {
		if (!this.googleUserRepository.findByEmail(user.getName()).isPresent()) {
			GoogleUser googleUser = GoogleUser.fromOauth2User(user);
			log.info(googleUser.toString());
			usuarioService.generarUsuarioParaOAuth2(googleUser);
		} else {
			log.info("bienvenido {}", user.getAttributes().get("given_name"));
		}
	}

}