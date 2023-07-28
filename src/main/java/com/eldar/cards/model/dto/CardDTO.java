package com.eldar.cards.model.dto;

import com.eldar.cards.model.Marca;
import com.eldar.cards.utils.DateUtils;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardDTO {

    private Long cardId;

    private Marca marca;

    private String numero;

    private String holder;

    private String fechaVencimiento;

    public Double getTasa() {
        return Optional.ofNullable(fechaVencimiento)
                .map(DateUtils::toDate)
                .map(d -> this.marca.getRateAlgorithm().apply(d))
                .orElse(0.0);
    }

    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj)
                .filter(CardDTO.class::isInstance)
                .map(o -> (CardDTO)o)
                .map(c -> Objects.equals(this.numero, c.numero))
                .orElse(Boolean.FALSE);
    }
}
