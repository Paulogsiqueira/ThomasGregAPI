package com.thomasgreg.clienteapi.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }
}