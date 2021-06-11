package com.claudia.springboot.creditcards.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudia.springboot.creditcards.app.models.dao.CardRepository;
import com.claudia.springboot.creditcards.app.models.dao.ClientRepository;
import com.claudia.springboot.creditcards.app.models.entity.Card;
import com.claudia.springboot.creditcards.app.models.entity.Client;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public CardController(CardRepository cardRepository, ClientRepository clientRepository) {
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
    }

    
    @PostMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid Card card, @PathVariable Integer id) {
    	
    	Optional<Card> optionalCard = cardRepository.findById(id);
    	if (!optionalCard.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
    	    	
    	
    	Optional<Card> optionalClient = cardRepository.findById(optionalCard.get().getId());
        if (!optionalClient.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        card.setClient(optionalClient.get().getClient());
        card.setCardDate(card.getCardDate());
        card.setCardNumber(card.getCardNumber());
        card.setCvv(card.getCvv());
        card.setName(card.getName());
        System.out.print(card.getName()+"**********");
        cardRepository.save(card);

        return ResponseEntity.ok().body("Se ha agregado una card al usuario "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        cardRepository.delete(optionalCard.get());

        return ResponseEntity.ok().body("Se ha eliminado");
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAll() {
        return ResponseEntity.ok(cardRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalCard.get());
    }
}
