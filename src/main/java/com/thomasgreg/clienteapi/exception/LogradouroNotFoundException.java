package com.thomasgreg.clienteapi.exception;

public class LogradouroNotFoundException extends RuntimeException {
    public LogradouroNotFoundException(String mensagem) {
        super(mensagem);
    }
}