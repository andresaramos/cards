package com.eldar.cards.service;

import com.eldar.cards.adapter.CardAdapter;
import com.eldar.cards.exceptions.InvalidCardDataException;
import com.eldar.cards.model.dto.CardDTO;
import com.eldar.cards.repository.CardRepository;
import com.eldar.cards.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardAdapter cardAdapter;

    @Transactional
    public CardDTO save(CardDTO cardDTO) {
        return Optional.ofNullable(cardDTO)
                .map(cardAdapter::toEntity)
                .map(cardRepository::save)
                .map(cardAdapter::toDTO)
                .orElseThrow(InvalidCardDataException::new);
    }

    public List<CardDTO> getAllCards() {
        return StreamSupport.stream(cardRepository.findAll().spliterator(), false)
                .map(cardAdapter::toDTO)
                .toList();
    }

    public Optional<CardDTO> getCard(Long id) {
        return cardRepository.findById(id)
                .map(cardAdapter::toDTO);
    }

    public boolean isValid(CardDTO card) {
        return Optional.ofNullable(card)
                .map(c -> DateUtils.toDate(c.getFechaVencimiento()))
                .map(v -> v.after(DateUtils.actualDate()))
                .orElse(Boolean.FALSE);
    }

    public boolean equals(CardDTO card1, CardDTO card2) {
        return Optional.ofNullable(card1)
                .map(c1 -> Optional.ofNullable(card2)
                                    .map(c2 -> c2.equals(c1))
                                    .orElse(Boolean.FALSE))
                .orElse(Boolean.FALSE);
    }
}
