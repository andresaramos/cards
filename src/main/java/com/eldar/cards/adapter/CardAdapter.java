package com.eldar.cards.adapter;

import com.eldar.cards.model.Card;
import com.eldar.cards.model.Marca;
import com.eldar.cards.model.dto.CardDTO;
import com.eldar.cards.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class CardAdapter {

    public CardDTO toDTO(Card card) {
        return CardDTO.builder()
                .cardId(card.getId())
                .fechaVencimiento(DateUtils.toString(card.getFechaVencimiento()))
                .holder(card.getHolder())
                .marca(Marca.of(card.getMarca()))
                .numero(card.getNumero())
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
