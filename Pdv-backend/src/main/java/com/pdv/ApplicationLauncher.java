package com.pdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationLauncher /*implements CommandLineRunner*/{
	
//	@Autowired
//	PerfilRepository perfilRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		var adminPerfil = Perfil.builder().codPerfil(CodPerfiles.PERFIL_ADMIN).txtPerfil("Administrador").build();
//		var gestorPerfil = Perfil.builder().codPerfil(CodPerfiles.PERFIL_GESTOR).txtPerfil("Gestor").build();
//		var clientePerfil = Perfil.builder().codPerfil(CodPerfiles.PERFIL_CLIENTE).txtPerfil("Cliente").build();
//		perfilRepository.save(adminPerfil);
//		perfilRepository.save(gestorPerfil);
//		perfilRepository.save(clientePerfil);
//	}

}
