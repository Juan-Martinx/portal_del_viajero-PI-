package com.pdv.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class FileSystemStorageService implements StorageService{
	
    @Value("${media.location}")
    private String mediaLocation;
	
	private Path rootLocation;
	
	/**
	 * Asigna una ruta para guardar archivos y 
	 * en caso de que no haya un directorio con ese
	 * path, lo crea.
	 */
	@Override
	@PostConstruct
	public void init() throws IOException{
		rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
	}

	/**
	 * Método que sirve para guardar los archivos
	 * proporcionados por los usuarios (fotos de perfil,
	 * fotos de alojamiento...)
	 */
	@Override
	public String store(MultipartFile file) {
		try {
			if(file.isEmpty()) {
				throw new RuntimeException("No se puede almacenar un archivo vacío");
			}
			String filename = file.getOriginalFilename();
			Path destinationFile = rootLocation.resolve(Paths.get(filename))
					.normalize().toAbsolutePath();
			try(InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			};
			return filename;	
		}catch(IOException e) {
			throw new RuntimeException("Failed to store file: " + e);
		}
	}

	/**
	 * Método que sirve para devolver un archivo.
	 */
	@Override
	public Resource loadAsSource(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("No se puede leer el archivo: " + filename);
			}
		}catch(MalformedURLException e) {
			throw new RuntimeException("No se puede leer el archivo: " + filename);
		}
	}
	
	
}
