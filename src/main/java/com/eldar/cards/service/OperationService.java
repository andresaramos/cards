package com.eldar.cards.service;

import com.eldar.cards.adapter.OperationAdapter;
import com.eldar.cards.exceptions.InvalidOperationDataException;
import com.eldar.cards.model.Operation;
import com.eldar.cards.model.dto.CardDTO;
import com.eldar.cards.model.dto.OperationDTO;
import com.eldar.cards.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationAdapter operationAdapter;

    @Transactional
    public Operation save(Operation operation) {
        return Optional.ofNullable(operation)
                .map(operationRepository::save)
                .orElseThrow(InvalidOperationDataException::new);
    }

    public List<Operation> getAllOperations() {
        return StreamSupport.stream(operationRepository.findAll().spliterator(), false)
                .toList();
    }

    public List<Operation> getAllOperationsOfCard(Long cardId) {
        return operationRepository.findByCardId(cardId);
    }

    public Optional<Operation> getOperation(Long id) {
        return operationRepository.findById(id);
    }

    public Optional<OperationDTO> getOperationExtendedInfo(Long id) {
        return operationRepository.findById(id)
                .map(operationAdapter::toDTO);
    }

    public boolean isValid(Operation operation) {
        return Optional.ofNullable(operation)
                .map(o -> o.getImporte() < 1000)
                .orElse(Boolean.FALSE);
    }
}
