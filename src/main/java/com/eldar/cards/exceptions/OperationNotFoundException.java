package com.eldar.cards.exceptions;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OperationNotFoundException extends RuntimeException {
}
