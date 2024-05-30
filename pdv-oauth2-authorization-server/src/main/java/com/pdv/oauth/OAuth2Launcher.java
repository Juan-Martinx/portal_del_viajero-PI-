package com.pdv.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OAuth2Launcher{
	
//	@Autowired
//	PerfilRepository perfilRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OAuth2Launcher.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		var adminPerfil = Perfil.builder().codPerfil(CodPerfiles.PERFIL_ADMIN).txtPerfil("Administrador").build();
//		var gestorPerfil = Perfil.builder().codPerfil(CodPerfiles.PERFIL_GESTOR).txtPerfil("Gestor").build();
//		var clientePerfil = Perfil.builder().codPerfil(CodPerfiles.PERFIL_CLIENTE).txtPerfil("Cliente").build();
//		var googlePerfil = Perfil.builder().codPerfil(CodPerfiles.OIDC_USER).txtPerfil("Usuario Google").build();
//
//		perfilRepository.save(adminPerfil);
//		perfilRepository.save(gestorPerfil);
//		perfilRepository.save(clientePerfil);
//		perfilRepository.save(googlePerfil);
//	}

}
