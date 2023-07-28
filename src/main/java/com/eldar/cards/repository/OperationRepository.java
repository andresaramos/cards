package com.eldar.cards.repository;

import com.eldar.cards.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {

    List<Operation> findByCardId(Long cardId);
}
