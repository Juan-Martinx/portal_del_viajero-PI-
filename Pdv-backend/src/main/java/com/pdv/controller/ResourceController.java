package com.pdv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.dto.GenericAPIMessageDTO;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/user")
    public ResponseEntity<GenericAPIMessageDTO> user(Authentication authentication){
        return ResponseEntity.ok(GenericAPIMessageDTO.builder().mensaje("Hola usuario").build());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('PERFIL_ADMIN')")
    public ResponseEntity<GenericAPIMessageDTO> admin(Authentication authentication){
        return ResponseEntity.ok(GenericAPIMessageDTO.builder().mensaje("Hola admin").build());
    }
}
