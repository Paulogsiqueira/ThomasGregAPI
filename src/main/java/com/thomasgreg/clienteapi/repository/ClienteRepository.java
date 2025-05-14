package com.thomasgreg.clienteapi.repository;

import com.thomasgreg.clienteapi.dto.ClienteDTO;
import com.thomasgreg.clienteapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);

    Optional<Cliente> findByEmail(String email);

    @Procedure(name = "InserirCliente")
    void inserirCliente(
            @Param("Nome") String nome,
            @Param("Email") String email,
            @Param("Logo") byte[] logo
    );

    @Query(value = "EXEC AtualizarCliente :Id, :Nome, :Email, :Logo", nativeQuery = true)
    Cliente atualizarCliente(
            @Param("Id") Long id,
            @Param("Nome") String nome,
            @Param("Email") String email,
            @Param("Logo") byte[] logo
    );

    @Procedure(name = "ListarTodosClientes")
    List<Cliente> listarTodos();

    @Procedure(name = "RemoverCliente")
    void removerCliente(@Param("Id") Long id);

}