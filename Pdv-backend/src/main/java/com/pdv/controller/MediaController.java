package com.pdv.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pdv.service.StorageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import pdv.commons.PathCommons;

@RestController
@RequestMapping(PathCommons.MEDIA_RESOURCES)
@AllArgsConstructor
public class MediaController {
	
	private final StorageService storageService;
	private final HttpServletRequest request;
	
	/**
	 * [CONTROLLER]
	 * Método que sirve para subir una imagen a
	 * una carpeta.
	 * @param multipartFile
	 * @return
	 */
	@PostMapping("/upload")
	public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile){
		String path = storageService.store(multipartFile);
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String url = ServletUriComponentsBuilder
				.fromHttpUrl(host)
				.path(PathCommons.MEDIA_RESOURCES + "/public/")
				.path(path)
				.toUriString();
		return Map.of("url", url);
	}
	
	/**
	 * [CONTROLLER]
	 * Método que retorna la imágen
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/public/{filename}")
	public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename) throws IOException{
		Resource file = storageService.loadAsSource(filename);
		String contentType = Files.probeContentType(file.getFile().toPath());
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, contentType)
				.body(file);
	}
}
