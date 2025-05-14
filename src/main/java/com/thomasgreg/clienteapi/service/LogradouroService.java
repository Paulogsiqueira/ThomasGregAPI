package com.thomasgreg.clienteapi.service;

import com.thomasgreg.clienteapi.dto.LogradouroDTO;
import com.thomasgreg.clienteapi.entity.Cliente;
import com.thomasgreg.clienteapi.entity.Logradouro;
import com.thomasgreg.clienteapi.exception.ClienteNotFoundException;
import com.thomasgreg.clienteapi.exception.LogradouroNotFoundException;
import com.thomasgreg.clienteapi.repository.ClienteRepository;
import com.thomasgreg.clienteapi.repository.LogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogradouroService {

    @PersistenceContext
    private EntityManager entityManager;

    private final LogradouroRepository logradouroRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Logradouro adicionarLogradouro(Long clienteId, Logradouro logradouro) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (!clienteOpt.isPresent()) {
            throw new ClienteNotFoundException("Cliente não encontrado.");
        }

        return logradouroRepository.inserirLogradouro(
                clienteId,
                logradouro.getRua(),
                logradouro.getNumero(),
                logradouro.getBairro(),
                logradouro.getCidade(),
                logradouro.getEstado()
        );

    }

    @Transactional
    public Logradouro atualizarLogradouro(Long logradouroId, LogradouroDTO logradouro) {
        Optional<Logradouro> logradouroExistente = logradouroRepository.findById(logradouroId);
        if (!logradouroExistente.isPresent()) {
            throw new LogradouroNotFoundException("Logradouro não encontrado.");
        }

        Logradouro logradouroAtualizado = logradouroRepository.atualizarLogradouro(
                logradouroId,
                logradouro.getClienteId(),
                logradouro.getRua(),
                logradouro.getNumero(),
                logradouro.getBairro(),
                logradouro.getCidade(),
                logradouro.getEstado()
        );

        entityManager.flush();
        entityManager.refresh(logradouroAtualizado);
        return  logradouroAtualizado;
    }

    @Transactional
    public List<Logradouro> buscarLogradourosPorCliente(Long clienteId) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (!clienteOpt.isPresent()) {
            throw new ClienteNotFoundException("Cliente não encontrado.");
        }
        return logradouroRepository.buscarLogradourosPorCliente(clienteId);
    }

    @Transactional
    public boolean removerLogradouro(Long logradouroId) {
        Optional<Logradouro> logradouroOpt = logradouroRepository.findById(logradouroId);
        if (logradouroOpt.isPresent()) {
            logradouroRepository.removerLogradouro(logradouroId);
            return true;
        }
        return false;
    }
}