package com.pdv.oauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdv.oauth.commons.PathCommons;
import com.pdv.oauth.dto.CreateClientDTO;
import com.pdv.oauth.dto.GenericAPIMessageDTO;
import com.pdv.oauth.service.ClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(PathCommons.AUTENTIFICATION_CLIENT)
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    /**
     * Método que sirve para crear un cliente de API
     * para oauth.
     * @param dto
     * @return
     */
    @PostMapping("/crear")
    public ResponseEntity<GenericAPIMessageDTO> crear(@RequestBody CreateClientDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(dto));
    }
}
