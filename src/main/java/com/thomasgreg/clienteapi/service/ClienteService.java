package com.thomasgreg.clienteapi.service;


import com.thomasgreg.clienteapi.dto.ClienteDTO;
import com.thomasgreg.clienteapi.entity.Cliente;
import com.thomasgreg.clienteapi.exception.ClienteNotFoundException;
import com.thomasgreg.clienteapi.exception.EmailAlreadyInUseException;
import com.thomasgreg.clienteapi.exception.InvalidLogotipoException;
import com.thomasgreg.clienteapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public Cliente criarCliente(ClienteDTO cliente, MultipartFile logotipo) throws IOException {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailAlreadyInUseException("E-mail já cadastrado.");
        }
        byte[] logoBytes = null;
        if (logotipo != null && !logotipo.isEmpty()) {
            logoBytes = logotipo.getBytes();
        }else{
            throw new InvalidLogotipoException("Logotipo enviado é inválido");
        }
        clienteRepository.inserirCliente(cliente.getNome(), cliente.getEmail(), logoBytes);

        return clienteRepository.findByEmail(cliente.getEmail())
                .orElseThrow(() -> new ClienteNotFoundException("Erro ao buscar cliente recém-criado."));
    }

    @Transactional
    public Cliente atualizarCliente(Long id, ClienteDTO cliente, MultipartFile logotipo) throws IOException {
        try {
            byte[] logoBytes = logotipo != null ? logotipo.getBytes() : null;
            return clienteRepository.atualizarCliente(id, cliente.getNome(), cliente.getEmail(), logoBytes);
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                if (sqlEx.getErrorCode() == 50000) { // Código de erro padrão do RAISERROR
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
                }
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar cliente");
        }
    }


    @Transactional
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.listarTodos();
    }

    @Transactional
    public boolean removerCliente(Long id) {
        try {
            clienteRepository.removerCliente(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
