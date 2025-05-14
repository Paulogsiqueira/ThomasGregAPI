package com.thomasgreg.clienteapi.dto;

import lombok.Data;

@Data
public class LogradouroDTO {
    private Long id;
    private Long clienteId;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
