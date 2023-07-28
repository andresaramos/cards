package com.eldar.cards.controller;

import com.eldar.cards.exceptions.CardNotFoundException;
import com.eldar.cards.model.dto.CardDTO;
import com.eldar.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/card", produces = "application/json")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<CardDTO> getCards() {
        return cardService.getAllCards();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO getCard(@PathVariable long id) {
        return cardService.getCard(id)
                .orElseThrow(CardNotFoundException::new);
    }

    @PostMapping(path = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO create(@RequestBody CardDTO cardDTO) {
        return cardService.save(cardDTO);
    }
}
