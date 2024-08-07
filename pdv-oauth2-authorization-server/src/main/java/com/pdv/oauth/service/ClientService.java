package com.pdv.oauth.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import com.pdv.oauth.dto.CreateClientDTO;
import com.pdv.oauth.dto.GenericAPIMessageDTO;
import com.pdv.oauth.model.Client;
import com.pdv.oauth.repository.ClientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Servicio para manejar operaciones relacionadas con clientes OAuth2.
 * <p>
 * Esta clase implementa {@link RegisteredClientRepository} y proporciona métodos para crear y recuperar clientes OAuth2.
 * </p>
 */
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id)
                .orElseThrow(()-> new RuntimeException("Client no encontrado"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(()-> new RuntimeException("Client no encontrado"));
        return Client.toRegisteredClient(client);
    }

    public GenericAPIMessageDTO create(CreateClientDTO dto){
        Client client = clientFromDto(dto);
        clientRepository.save(client);
        return GenericAPIMessageDTO.builder()
        		.mensaje("Client " + client.getClientId() + " creado")
        		.estado(HttpStatus.CREATED)
        		.fechaYHora(LocalDateTime.now())
        		.build();
    }

    // private methods
    private Client clientFromDto(CreateClientDTO dto){
        Client client = Client.builder()
                .clientId(dto.getClientId())
                .clientSecret(passwordEncoder.encode(dto.getClientSecret()))
                .authenticationMethods(dto.getAuthenticationMethods())
                .authorizationGrantTypes(dto.getAuthorizationGrantTypes())
                .redirectUris(dto.getRedirectUris())
                .scopes(dto.getScopes())
                .requireProofKey(dto.isRequireProofKey())
                .build();
        return client;
    }

}
