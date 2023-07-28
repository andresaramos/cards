package com.eldar.cards.controller;

import com.eldar.cards.adapter.CardAdapter;
import com.eldar.cards.exceptions.CardNotFoundException;
import com.eldar.cards.model.Operation;
import com.eldar.cards.model.dto.OperationDTO;
import com.eldar.cards.service.CardService;
import com.eldar.cards.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class OperationController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardAdapter cardAdapter;

    @Autowired
    private OperationService operationService;

    @GetMapping(path = "/operation")
    @ResponseStatus(HttpStatus.OK)
    public List<Operation> getOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping(path = "/card/{cardId}/operation")
    @ResponseStatus(HttpStatus.OK)
    public List<Operation> getOperationsByCard(@PathVariable long cardId) {
        return operationService.getAllOperationsOfCard(cardId);
    }

    @GetMapping(path = "/operation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Operation getOperation(@PathVariable long id) {
        return operationService.getOperation(id)
                .orElseThrow(CardNotFoundException::new);
    }

    @GetMapping(path = "/operation/{id}/extend")
    @ResponseStatus(HttpStatus.OK)
    public OperationDTO getOperationExtendInfo(@PathVariable long id) {
        return operationService.getOperationExtendedInfo(id)
                .orElseThrow(CardNotFoundException::new);
    }

    @PostMapping(path = "/card/{idCard}/operation")
    @ResponseStatus(HttpStatus.CREATED)
    public Operation create(@PathVariable long idCard, @RequestBody Operation operation) {
        return Optional.of(idCard)
                       .flatMap(iCard -> cardService.getCard(iCard)
                                                .map(cardAdapter::toEntity)
                                                .map(card -> { operation.setCard(card);
                                                               return operation;
                                                             })
                        .map(operationService::save))
                .orElseThrow(CardNotFoundException::new);

    }
}
