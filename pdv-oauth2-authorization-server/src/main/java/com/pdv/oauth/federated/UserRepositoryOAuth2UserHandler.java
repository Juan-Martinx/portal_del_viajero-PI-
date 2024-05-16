package com.pdv.oauth.federated;

import java.util.function.Consumer;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.pdv.oauth.model.GoogleUser;
import com.pdv.oauth.repository.GoogleUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

    private final GoogleUserRepository googleUserRepository;

	@Override
	public void accept(OAuth2User user) {
		if (!this.googleUserRepository.findByEmail(user.getName()).isPresent()) {
			GoogleUser googleUser = GoogleUser.fromOauth2User(user);
			log.info(googleUser.toString());
			this.googleUserRepository.save(googleUser);
		} else {
			log.info("bienvenido {}", user.getAttributes().get("given_name"));
		}
	}

}