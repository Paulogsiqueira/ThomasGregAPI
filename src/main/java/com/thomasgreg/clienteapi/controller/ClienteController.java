package com.thomasgreg.clienteapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomasgreg.clienteapi.dto.ClienteDTO;
import com.thomasgreg.clienteapi.entity.Cliente;
import com.thomasgreg.clienteapi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ObjectMapper objectMapper;
    private final ClienteService clienteService;

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> criarCliente(
            @RequestParam("cliente") String cliente,
            @RequestParam("logotipo") MultipartFile logotipo) throws IOException {

        ClienteDTO novoCliente = objectMapper.readValue(cliente, ClienteDTO.class);
        Cliente clienteAdicionado = clienteService.criarCliente(novoCliente, logotipo);
        return new ResponseEntity<>(clienteAdicionado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable Long id,
            @RequestPart("cliente") String cliente,
            @RequestPart("logotipo") MultipartFile logotipo) throws IOException {

        ClienteDTO novoCliente = objectMapper.readValue(cliente, ClienteDTO.class);
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, novoCliente, logotipo);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Cliente>> listarTodosClientes() {
        List<Cliente> clientes = clienteService.listarTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        if (clienteService.removerCliente(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}