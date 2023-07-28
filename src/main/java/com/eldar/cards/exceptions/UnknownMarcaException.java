package com.eldar.cards.exceptions;

public class UnknownMarcaException extends RuntimeException {
    public UnknownMarcaException(String name) {
        super("Could not find marca with name: " + name);
    }
}   