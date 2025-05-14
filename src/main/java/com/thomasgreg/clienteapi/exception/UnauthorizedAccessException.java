package com.thomasgreg.clienteapi.exception;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String mensagem) {
        super(mensagem);
    }
}
