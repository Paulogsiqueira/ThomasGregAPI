package com.thomasgreg.clienteapi.exception;

public class InvalidLogotipoException extends RuntimeException {
    public InvalidLogotipoException(String mensagem) {
        super(mensagem);
    }
}