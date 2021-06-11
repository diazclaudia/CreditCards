package com.claudia.springboot.creditcards.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudia.springboot.creditcards.app.models.dao.CardRepository;
import com.claudia.springboot.creditcards.app.models.dao.ClientRepository;
import com.claudia.springboot.creditcards.app.models.entity.Client;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository, CardRepository cardRepository) {
        this.clientRepository = clientRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
    	Client savedClient = clientRepository.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @Valid @RequestBody Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        client.setId(optionalClient.get().getId());
        clientRepository.save(client);

        return ResponseEntity.ok().body("Se ha modificado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        clientRepository.delete(optionalClient.get());

        return ResponseEntity.ok().body("Se ha eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalClient.get());
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

}