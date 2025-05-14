package com.thomasgreg.clienteapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomasgreg.clienteapi.dto.LogradouroDTO;
import com.thomasgreg.clienteapi.entity.Logradouro;
import com.thomasgreg.clienteapi.service.LogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/logradouros")
@RequiredArgsConstructor
public class LogradouroController {

    private final ObjectMapper objectMapper;
    private final LogradouroService logradouroService;

    @PostMapping("/{clienteId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Logradouro> adicionarLogradouro(
            @PathVariable Long clienteId,
            @RequestParam("logradouro") String logradouro) throws IOException {

        Logradouro novoLogradouro = objectMapper.readValue(logradouro, Logradouro.class);
        Logradouro logradouroAdicionado = logradouroService.adicionarLogradouro(clienteId, novoLogradouro);
        return new ResponseEntity<>(logradouroAdicionado, HttpStatus.CREATED);
    }

    @PutMapping("/{logradouroId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Logradouro> atualizarLogradouro(
            @PathVariable Long logradouroId,
            @RequestParam("logradouro") String logradouro) throws IOException {

        LogradouroDTO novoLogradouro = objectMapper.readValue(logradouro, LogradouroDTO.class);
        novoLogradouro.setId(logradouroId);
        Logradouro logradouroAtualizado = logradouroService.atualizarLogradouro(logradouroId, novoLogradouro);
        if (logradouroAtualizado != null) {
            return new ResponseEntity<>(logradouroAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Logradouro>> buscarLogradourosPorCliente(@PathVariable Long clienteId) {
        List<Logradouro> logradouros = logradouroService.buscarLogradourosPorCliente(clienteId);
        return new ResponseEntity<>(logradouros, HttpStatus.OK);
    }

    @DeleteMapping("/{logradouroId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removerLogradouro(@PathVariable Long logradouroId) {
        if (logradouroService.removerLogradouro(logradouroId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}