package com.thomasgreg.clienteapi.exception;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String mensagem) {
        super(mensagem);
    }
}