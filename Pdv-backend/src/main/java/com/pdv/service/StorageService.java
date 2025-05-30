package com.pdv.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz del servicio de almacenamiento.
 * (Esta se usa en FileSystemStorageService.java)
 */
public interface StorageService {
	
	void init() throws IOException;
	
	String store(MultipartFile file);
	
	Resource loadAsSource(String filename);

}
