package com.eldar.cards.adapter;

import com.eldar.cards.model.Card;
import com.eldar.cards.model.Marca;
import com.eldar.cards.model.Operation;
import com.eldar.cards.model.dto.CardDTO;
import com.eldar.cards.model.dto.OperationDTO;
import com.eldar.cards.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationAdapter {

    @Autowired
    private CardAdapter cardAdapter;

    public OperationDTO toDTO(Operation operation) {
        return OperationDTO.builder()
                .operationId(operation.getId())
                .importe(operation.getImporte())
                .card(cardAdapter.toDTO(operation.getCard()))
                .build();
    }

    public Card toEntity(CardDTO dto) {
        return Card.builder()
                .id(dto.getCardId())
                .fechaVencimiento(DateUtils.toDate(dto.getFechaVencimiento()))
                .holder(dto.getHolder())
                .marca(dto.getMarca().name())
                .numero(dto.getNumero())
                .build();
    }
}
