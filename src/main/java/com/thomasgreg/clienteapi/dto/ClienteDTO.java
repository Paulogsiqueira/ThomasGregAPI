package com.thomasgreg.clienteapi.dto;


import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
}